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
    public static int me;
    public showgame s;
    public Button b;
    private void Awake()
    {
        test.t = this;
        HandlerMap.registeHandler(100, new testHandler());
        HandlerMap.registeHandler(MsgCode.gameUpdate, new undateMapHandler());
        b.onClick.AddListener(send);
        //开启一个线程用ManualResetEvent阻塞该线程计时 如果超时就主动调用socket close
        Thread t = new Thread(new ThreadStart(connect));
        t.Start();

    }
    Connection c;
    IEnumerator Start()
    {
        yield return StartCoroutine(LoadResources.loadPicture("block.jpg"));
        s = new showgame();

    }


    private void connect()
    {
        c = new Connection();
        c.Connect("192.168.0.115", 8081);
    }



    // Update is called once per frame
    void Update()
    {
        MsgDistribution.getInstance().run();
# if UNITY_ANDROID
        elsfk_input_changeinput(FingerTouch());
#endif
#if UNITY_EDITOR || UNITY_STANDALONE_WIN
        elsfk_input_changeinput(cpinput());
#endif

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


    public void elsfk_input_changeinput(FingerTouchType type)
    {
        gameinput g = new gameinput();
        int state = 8;
        if (type == FingerTouchType.type_LeftMove)
            state = 0;
        else if (type == FingerTouchType.type_RightMove)
            state = 1;
        else if (type == FingerTouchType.type_UpMove)
            state = 3;
        else if (type == FingerTouchType.type_DownMove)
            state = 2;
        if (state == 8)
            return;

        g.Code = state;
        c.Send(MsgCode.gameUpdate, g);
    }


    public enum FingerTouchType
    {
        type_LeftMove,
        type_RightMove,
        type_UpMove,
        type_DownMove,
        noinput
    }


    FingerTouchType cpinput()
    {
        if (Input.GetKeyDown(KeyCode.LeftArrow))
            return FingerTouchType.type_LeftMove;
        else if (Input.GetKeyDown(KeyCode.RightArrow))
            return FingerTouchType.type_RightMove;
        else if (Input.GetKeyDown(KeyCode.UpArrow))
            return FingerTouchType.type_UpMove;
        else if (Input.GetKeyDown(KeyCode.DownArrow))
            return FingerTouchType.type_DownMove;
        else if (Input.GetKey(KeyCode.DownArrow))
            return FingerTouchType.type_DownMove;
        return FingerTouchType.noinput;
    }


    //记录触屏位置
    Vector2 screenPos = new Vector2();
    float time;
    /// <summary>
    /// 判断函数,放在update里执行
    /// </summary>
    FingerTouchType FingerTouch()
    {
        if (Input.touchCount <= 0)
            return FingerTouchType.noinput;
        if (Input.touchCount == 1)
        {
            if (Input.touches[0].phase == TouchPhase.Began)
            {
                //记录位置
                screenPos = Input.touches[0].position;

            }
            else if (Input.touches[0].phase == TouchPhase.Moved)
            {

            }

            if (Input.touches[0].phase == TouchPhase.Ended && Input.touches[0].phase != TouchPhase.Canceled)
            {
                Vector2 pos = Input.touches[0].position;

                return CheckTouch(screenPos, pos);
            }
            return FingerTouchType.noinput;
        }
        else if (Input.touchCount > 1)
        {
            return FingerTouchType.noinput;
        }
        return FingerTouchType.noinput;

    }

    FingerTouchType CheckTouch(Vector2 start, Vector2 end)
    {
        if (Vector2.Distance(start, end) <= 100)
            return FingerTouchType.noinput;
        if (Mathf.Abs(start.x - end.x) > Mathf.Abs(start.y - end.y))
        {
            if (start.x > end.x)
            {
                //左滑
                return FingerTouchType.type_LeftMove;
            }
            else
            {
                //右滑
                return FingerTouchType.type_RightMove;
            }
        }
        else//垂直滑动
        {
            if (start.y > end.y)
            {
                //下滑
                return FingerTouchType.type_DownMove;
            }
            else
            {
                //上滑
                return FingerTouchType.type_UpMove;
            }
        }
    }
}


