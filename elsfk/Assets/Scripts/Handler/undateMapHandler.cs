using UnityEngine;
using UnityEditor;

public class undateMapHandler : MyHandler
{
    public override void doHandler(SocketModel arg1)
    {
        Google.Protobuf.MessageParser<gamedata> d = new Google.Protobuf.MessageParser<gamedata>(getMapMessage);
        gamedata msg = d.ParseFrom(arg1.Data);

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

    gamedata getMapMessage()
    {
        return new gamedata();
    }

    void doAction(gamedata msg)
    {
        int[,] map = new int[msg.Column, msg.Row];
        for (int i = 0; i < msg.Column; i++)
        {
            for (int j = 0; j < msg.Row; j++)
            {
                map[i, j] = msg.Data[i * msg.Row + j];
            }
        }

        test.t.s.elsfk_input_drawgame(map, msg);
    }
}