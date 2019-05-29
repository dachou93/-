using UnityEngine;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Threading;

//消息分发
public class MsgDistribution
{
    private static MsgDistribution instance =null;

    public static MsgDistribution getInstance()
    {
        if (instance == null)
        {
            instance = new MsgDistribution();
        }
        return instance;
    }
    private MsgDistribution()
    { }
    //消息
    private Queue<SocketModel> msgList = new Queue<SocketModel>();

    private Queue<Action> actions = new Queue<Action>();

    //主线程没帧执行
    public void run()
    {
        lock (actions)
        {
            if (actions.Count > 0)
            {
                for (int i = 0; i < actions.Count; i++)
                {
                    actions.Dequeue()();
                }
                Monitor.Pulse(actions);

            }
        }
    }

    public void add_model(SocketModel model)
    {
        lock (msgList)
        {
            if (msgList.Count>=15)
            Monitor.Wait(msgList);
            msgList.Enqueue(model);
        }
    }




    public void add_action(Action a)
    {
        lock (actions)
        {
            //Debug.Log(actions.Count);
            if (actions.Count >= 15)
                Monitor.Wait(actions);
            actions.Enqueue(a);
        }
    }

   
}