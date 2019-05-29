using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Text;
using UnityEngine;
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
        shoupingtai.text = "ANDROID";
#elif UNITY_IPHONE && !UNITY_EDITOR
            path ="file://"+ Application.dataPath  + "/Raw/";
        shoupingtai.text = "IPHONE";
#elif UNITY_WINDOWS || UNITY_EDITOR
        path = "file://" + Application.dataPath + "/StreamingAssets/";
        //shoupingtai.text = "WINDOWS";
#elif UNITY_WEBGL
        path=Application.dataPath + "/StreamingAssets/";
        shoupingtai.text = "WEBGL";
#endif
    }
    public static IEnumerator loadPicture(string name)
    {
        getPath();
        var pathname = Application.dataPath + "/StreamingAssets/" + name;
        var filepath = path + name;
        //shoupath.text = filepath;
        using (WWW www = new WWW(filepath))
        {
            //shouresult.text = "jzz";
            yield return www;
            if (www.error == null)
            {
                sp = www.texture;
                //shouresult.text = "jzcg";
            }
            else
            {
                //shouresult.text = "jzsb";
            }
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

