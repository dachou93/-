using Google.Protobuf;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;
using UnityEngine;

public class Connection 
{
    //常量
    const int BUFFER_SIZE = 1024;
    //Socket
    private Socket socket;
    //Buff
    private byte[] readBuff = new byte[BUFFER_SIZE];
    private int buffCount = 0;
    //沾包分包
    private Int32 msgLength = 0;
    private byte[] lenBytes = new byte[sizeof(Int32)];

    ///状态
    public enum Status
    {
        None,
        Connected,
    };
    public Status status = Status.None;


    //连接服务端
    public bool Connect(string host, int port)
    {
        try
        {
            //socket
            socket = new Socket(AddressFamily.InterNetwork,
                      SocketType.Stream, ProtocolType.Tcp);
            //Connect
            socket.Connect(host, port);
            //BeginReceive
            socket.BeginReceive(readBuff, buffCount,
                      BUFFER_SIZE - buffCount, SocketFlags.None,
                      ReceiveCb, readBuff);
            Debug.Log("连接成功");
            //状态
            status = Status.Connected;

            return true;
        }
        catch (Exception e)
        {
            Debug.Log("连接失败:" + e.Message);
            return false;
        }
    }

    //关闭连接
    public bool Close()
    {
        try
        {
            socket.Close();
            return true;
        }
        catch (Exception e)
        {
            Debug.Log("关闭失败:" + e.Message);
            return false;
        }
    }

    //接收回调
    private void ReceiveCb(IAsyncResult ar)
    {
        try
        {
            Debug.Log("ReceiveCb开始");
            int count = socket.EndReceive(ar);
            buffCount = buffCount + count;
            ProcessData();
            socket.BeginReceive(readBuff, buffCount,
                     BUFFER_SIZE - buffCount, SocketFlags.None,
                     ReceiveCb, readBuff);
        }
        catch (Exception e)
        {
            Debug.Log("ReceiveCb失败:" + e.Message);
            status = Status.None;
        }
    }

    //消息处理
    private void ProcessData()
    {

        if (buffCount < sizeof(Int32))
            return;
        //取出消息长度
        Array.Copy(readBuff, lenBytes, sizeof(Int32));
        msgLength = BitConverter.ToInt32(ReverseBytes(lenBytes), 0);
        //Debug.Log(msgLength);
        if (buffCount < msgLength + sizeof(Int32) + sizeof(short))
        {
            return;
        }
        //取出操作码
        byte[] c = new byte[sizeof(short)];
        Array.Copy(readBuff, sizeof(Int32), c, 0, sizeof(short));
        short cmdCode = BitConverter.ToInt16(ReverseBytes(c),0);
        //构造socket模型
        SocketModel model = new SocketModel();
        model.CmdCode = cmdCode;
        model.Data = new byte[msgLength];
        Array.Copy(readBuff, sizeof(Int32) + sizeof(short), model.Data,0,msgLength);
        //分发处理消息
        doHandler(model);
        //情除已经处理的消息
        int count = buffCount - msgLength - sizeof(Int32)-sizeof(short);
        Array.Copy(readBuff, sizeof(Int32) + sizeof(short) + msgLength, readBuff, 0, count);
        buffCount = count;
        //尾递归
        if (buffCount > 0)
        {
            ProcessData();
        }
    }


    private void doHandler(SocketModel model)
    {
        HandlerMap.getHandlersByType(model.CmdCode).doHandler(model);
        
    }


    public bool Send(String msg)
    {
        if (status != Status.Connected)
        {
            Debug.LogError("[Connection]还没链接就发送数据是不好的");
            return true;
        }

        byte[] b = Encoding.UTF8.GetBytes(msg);
        socket.Send(b);
        return true;
    }


    public bool Send(SocketModel msg)
    {
        if (status != Status.Connected)
        {
            Debug.LogError("[Connection]还没链接就发送数据是不好的");
            return true;
        }
        int dataLength = msg.Data == null ? 0 : msg.Data.Length;
        List<byte> b = new List<byte>();
       //c#window平台小端字节序java大端字节序要翻转
        b.AddRange(ReverseBytes(BitConverter.GetBytes(dataLength)));
        b.AddRange(ReverseBytes(BitConverter.GetBytes(msg.CmdCode)));
        if (dataLength > 0)
            b.AddRange(msg.Data);
        byte[] s = b.ToArray();
        socket.Send(s);
        return true;
      
    }

    public bool Send<T>(short cmdCode, T msg) where T: IMessage
    {
        SocketModel m = new SocketModel();
        m.CmdCode = cmdCode;
        m.Data = msg.ToByteArray();
        return Send(m);
    }


    private byte[] ReverseBytes(byte[] bytes)
    {
        Array.Reverse(bytes);
        return bytes;
    }




    public void Update()
    {
        
       
    }
}
