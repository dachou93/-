using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Text;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;

public class LoadResources
{
    public static string path = "";
    public static Texture2D sp;
    //public static Text shoupath = GameObject.Find("path").GetComponent<Text>();
    //public static Text shouresult = GameObject.Find("result").GetComponent<Text>();
    //public static Text shoupingtai = GameObject.Find("pingtai").GetComponent<Text>();
    public static string str;
    public static void getPath()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        path = "jar:file:///"+Application.dataPath+"!/assets/";
       
#elif UNITY_IPHONE && !UNITY_EDITOR
            path ="file://"+ Application.dataPath  + "/Raw/";
        
#elif UNITY_WINDOWS || UNITY_EDITOR
        path = "file://" + Application.dataPath + "/StreamingAssets/";
        
#elif UNITY_WEBGL
        path=Application.dataPath + "/StreamingAssets/";
       
#endif
    }
    public static IEnumerator loadPicture(string name)
    {
        //getPath();
        //var pathname = Application.dataPath + "/StreamingAssets/" + name;
        //var filepath = path + name;
        var uri = new System.Uri(Path.Combine(Application.streamingAssetsPath, "block.jpg"));
        UnityWebRequest w = new UnityWebRequest(uri);
        DownloadHandlerTexture texDl = new DownloadHandlerTexture(true);
        w.downloadHandler = texDl;
        yield return w.SendWebRequest();
        if (w.isDone)
        {
            sp = texDl.texture;
            if (sp == null)
                test.t.closeImg();
        }
    }
    //public static IEnumerator load_editor(string file_name)
    //{
    //    getPath();
    //    //var pathname = Application.dataPath + "/StreamingAssets/" + name;
    //    string jsonStr = "";
    //    var filepath = path + file_name;
    //    shoupath.text = filepath;
    //    using (WWW www = new WWW(filepath))
    //    {
    //        shouresult.text = "jzz";
    //        yield return www;
    //        if (www.error == null)
    //        {
    //            jsonStr = www.text;
    //            str = jsonStr;
    //            shouresult.text = "jzcg";
    //        }
    //        else
    //        {
    //            shouresult.text = "jzsb";
    //        }
    //    }
    //    Debug.Log("json:" + jsonStr);
    //}
}

