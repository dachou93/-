using System.Collections;
using System.Collections.Generic;
using System.Threading;
using UnityEngine;
using UnityEngine.UI;

public class test : MonoBehaviour
{
    public static test t;
    public Image i;
    int closeCount;

    public showgame s;
    public Button b;
    private void Awake()
    {
        test.t = this;
        HandlerMap.registeHandler(100, new testHandler());
        HandlerMap.registeHandler(MsgCode.gameUpdate, new undateMapHandler());
        b.onClick.AddListener(send);
        //开启一个线程用ManualResetEvent阻塞该线程计时 如果超时就停止连接
        Thread t = new Thread(new ThreadStart(connect));
        t.Start();

    }
    Connection c ;
   IEnumerator Start()
    {
        yield return StartCoroutine(LoadResources.loadPicture("block.jpg"));
        s = new showgame();

    }


    private void connect()
    {
        c = new Connection();
        c.Connect("127.0.0.1", 8081);
    }



    // Update is called once per frame
    void Update()
    {
        MsgDistribution.getInstance().run();
        elsfk_input_changeinput();
    }

    public void send()
    {

        nulldata t = new nulldata();
        c.Send(MsgCode.gameStart, t);

        i.gameObject.SetActive(false);
        b.gameObject.SetActive(false);
       
    }

    private void OnDestroy()
    {
        if (c != null)
            c.Close();
    }

    public void closeImg()
    {
        closeCount++;
        i.gameObject.SetActive(false);
        Debug.Log(closeCount);
    }


    public void elsfk_input_changeinput()
    {
        gameinput g = new gameinput();
        int state = 8;
        if (Input.GetKeyDown(KeyCode.LeftArrow))
            state = 0;
        else if (Input.GetKeyDown(KeyCode.RightArrow))
            state = 1;
        else if (Input.GetKeyDown(KeyCode.UpArrow))
            state = 3;
        else if (Input.GetKeyDown(KeyCode.DownArrow))
            state = 2;
        else if (Input.GetKey(KeyCode.DownArrow))
            state = 2;
        if (state == 8)
            return;

        g.Code = state;
        c.Send(MsgCode.gameUpdate, g);
    }
}
