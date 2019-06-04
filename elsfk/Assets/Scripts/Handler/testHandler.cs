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
        Debug.Log(msg.Message);
    }

    public testmsg gettestMsg()
    {
        return new testmsg();
    }


    public void doAction(testmsg msg)
    {
        if (msg.Message == "0")
        {
            test.me = -1;
        }
        else if (msg.Message == "1")
        {
            test.me = 1;
        }
        else
        {
            test.t.b.gameObject.SetActive(true);
        }
    }
    
}
