using UnityEngine;
using UnityEditor;

public class undateMapHandler : MyHandler
{
    public override void doHandler(SocketModel arg1)
    {
        Google.Protobuf.MessageParser<gamedata2> d = new Google.Protobuf.MessageParser<gamedata2>(getMapMessage);
        gamedata2 msg = d.ParseFrom(arg1.Data);

        //int[,] map = new int[msg.Row, msg.Column];
        //Debug.Log(msg.Row);
        //for (int i = 0; i < msg.Row; i++)
        //{
        //    for (int j = 0; j < msg.Column; j++)
        //    {
        //        map[i, j] = msg.Data[i * msg.Column + j];
        //    }
        //}

        //for (int i = 0; i < msg.Data.Count; i++)
        //{ 
        //    Debug.Log(msg.Data[i]);
        //}
        Debug.Log("--------------------------");
        MsgDistribution.getInstance().add_action(delegate { doAction(msg); });
    }

    gamedata2 getMapMessage()
    {
        return new gamedata2();
    }

    void doAction(gamedata2 msg)
    {
        int[,] map = new int[10, 20];
        int[,] map2 = new int[10, 20];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 20; j++)
            {
                map2[i, j] = msg.Datas[0].Data[i * 20 + j];
                map[i, j] = msg.Datas[1].Data[i * 20 + j];
            }
        }

        test.t.s.elsfk_input_drawgame(map,map2);
        Debug.Log(msg.Datas.Count);
    }
}