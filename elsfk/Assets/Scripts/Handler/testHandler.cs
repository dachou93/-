using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class testHandler : MyHandler
{
    public override void doHandler(SocketModel arg1)
    {
        Google.Protobuf.MessageParser<testmsg> d = new Google.Protobuf.MessageParser<testmsg>(gettestMsg);
        testmsg msg = d.ParseFrom(arg1.Data);
        MsgDistribution.getInstance().add_action( delegate { doAction(msg); });
        Debug.Log("游戏概述");
    }

    public testmsg gettestMsg()
    {
        return new testmsg();
    }


    public void doAction(testmsg msg)
    {
        test.t.b.gameObject.SetActive(true);
    }
    
}
