using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class testHandler : MyHandler
{
    public override void doHandler(SocketModel arg1)
    {
        Google.Protobuf.MessageParser<testmsg> d = new Google.Protobuf.MessageParser<testmsg>(gettestMsg);
        testmsg msg = d.ParseFrom(arg1.Data);
        //Debug.Log(msg.Message+"輔助");
        MsgDistribution.getInstance().add_action( delegate { doAction(msg); });
    }

    public testmsg gettestMsg()
    {
        return new testmsg();
    }


    public void doAction(testmsg msg)
    {
        //Debug.Log(msg.Message+"主");
        test.t.closeImg();
    }
    
}
