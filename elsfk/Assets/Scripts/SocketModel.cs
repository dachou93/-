using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SocketModel
{
    private short cmdCode;//操作碼
    private byte[] data;//protobuf數據主體

    public short CmdCode { get => cmdCode; set => cmdCode = value; }
    public byte[] Data { get => data; set => data = value; }
}
