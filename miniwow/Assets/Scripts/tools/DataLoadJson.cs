using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using UnityEngine;

namespace Assets.scripts.tools
{

    public class DataLoadJson
    {
        public static string path = "";
        public static void getPath()
        {
#if UNITY_EDITOR
            path = Application.dataPath + "/json/";
#elif UNITY_STANDALONE_WIN
            path = Application.streamingAssetsPath  + "/json/";
#elif UNITY_ANDROID || UNITY_IOS
                             path = Application.persistentDataPath+"/json/" ;
#elif UNITY_WINDOWS
                             path = Application.persistentDataPath+"/json/";
#endif

        }

        public static string loadFilefromJson(string file_name)
        {

            string str = "";
#if UNITY_EDITOR
            str = load_editor(file_name);
#elif  UNITY_STANDALONE_WIN
            str = load_editor(file_name);
#elif UNITY_ANDROID || UNITY_IPHONE
              str = load_android_ios(file_name);
#elif UNITY_WINDOWS
              str = load_android_ios(file_name);
#endif
            return str;
        }

        public static string load_editor(string file_name)
        {
            getPath();
            ////Debug.Log("loadFilefromJson path:" + path);
            string jsonStr = "";
            if (!File.Exists(path + file_name))
            {
                //Debug.Log(path + file_name);
                return null;
            }
            ////Debug.Log("loadFilefromJson path:" + path + file_name);
            // //Debug.Log(path + file_name);
            StreamReader sr = new StreamReader(path + file_name, Encoding.Default);
            if (sr == null)
            {
                //Debug.Log("return2222");
                return null;
            }
            jsonStr = sr.ReadToEnd();
            ////Debug.Log("json:" + jsonStr);
            //string[] json =  jsonStr.Split(']');
            Debug.Log("json:" + jsonStr);
            return jsonStr;
        }

        public static string load_android_ios(string file_name)
        {
            file_name = file_name.Replace(".json", "");
            //Debug.Log("load_android_ios file_name:" + file_name);
            ////Debug.Log("GlobalData.jsonDirectory Count:" + GlobalData.jsonDirectory.Count);

            //return GlobalData.jsonDirectory[file_name];
            return "";
        }



        public static List<ListAndList> getListFromJson(string json)
        {
            ////Debug.Log("json size:" + json.Length);
            List<ListAndList> listRoot = new List<ListAndList>();
            //开始记录分割时间
            System.Diagnostics.Stopwatch sw = new System.Diagnostics.Stopwatch();
            sw.Start();
            // string[] jsonRoot = json.Split(new String[2] { "],", "]\r\n" }, StringSplitOptions.RemoveEmptyEntries);
            string[] jsonRoot = json.Split(new String[] { "]\r\n," }, StringSplitOptions.RemoveEmptyEntries);

            //结束记录分割时间
            sw.Stop();

            //  //Debug.Log("消耗的时间:" + sw.ElapsedMilliseconds);//以毫秒为单位

            // //Debug.Log("jsonRoot[0]:" + jsonRoot[0]);
            // //Debug.Log("jsonRoot[1]:" + jsonRoot[1]);
            // //Debug.Log("jsonRoot[2]:" + jsonRoot[2]);

            for (int i = 0; i < jsonRoot.Length; i++)
            {
                ////Debug.Log("jsonRoot[0]:" + jsonRoot[i]);
                ListAndList listAndList = new ListAndList();
                jsonRoot[i] = jsonRoot[i].Replace("[[", "");
                jsonRoot[i] = jsonRoot[i].Replace("[", "");
                jsonRoot[i] = jsonRoot[i].Replace("]\r\n", "");
                jsonRoot[i] = jsonRoot[i].Replace("]", "");

                ////Debug.Log("jsonRoot[i]:" + jsonRoot[i]);
                string[] jsonEvery = jsonRoot[i].Split(',');
                ////Debug.Log("jsonEvery size:" + jsonEvery.Length);
                for (int j = 0; j < jsonEvery.Length; j++)
                {
                    listAndList.AddList(jsonEvery[j]);
                }
                listRoot.Add(listAndList);
            }

            return (listRoot.Count <= 0) ? null : listRoot;
        }

        /*一个键 一个值的格式的json*/

        public static List<ListAndList> getListFromJson2(string json)
        {
            List<ListAndList> listRoot = new List<ListAndList>();
            json = json.Replace("[", "");
            json = json.Replace("]", "");
            ////Debug.Log("getListFromJson2:" + json);

            string[] jsonRoot = json.Split(new String[] { "},", "" }, StringSplitOptions.RemoveEmptyEntries);
            ////Debug.Log("getListFromJson2 size:" + jsonRoot.Length);
            for (int i = 0; i < jsonRoot.Length; i++)
            {
                ////Debug.Log("getListFromJson2 jsonRoot[i]:" + jsonRoot[i]);
                ListAndList listAndList = new ListAndList();
                string[] jsonEvery = jsonRoot[i].Split(',');
                // //Debug.Log("getListFromJson2 jsonEvery size:" + jsonEvery.Length);

                for (int j = 0; j < jsonEvery.Length; j++)
                {
                    //  //Debug.Log("jsonEvery:" + jsonEvery[j]);
                    string tmp = "";
                    string[] jsonEvery111 = jsonEvery[j].Split(':');
                    tmp = jsonEvery111[1].Replace("}", "");
                    tmp = tmp.Replace("\"", "");

                    listAndList.AddList(tmp);
                }
                listRoot.Add(listAndList);
            }
            return (listRoot.Count <= 0) ? null : listRoot;
        }

        //解析语言表
        public static List<ListAndList> getListFromJson3(string json)
        {
            ////Debug.Log("json size:" + json.Length);
            List<ListAndList> listRoot = new List<ListAndList>();
            //开始记录分割时间
            System.Diagnostics.Stopwatch sw = new System.Diagnostics.Stopwatch();
            sw.Start();
            // string[] jsonRoot = json.Split(new String[2] { "],", "]\r\n" }, StringSplitOptions.RemoveEmptyEntries);
            string[] jsonRoot = json.Split(new String[] { "]\r\n," }, StringSplitOptions.RemoveEmptyEntries);

            //结束记录分割时间
            sw.Stop();

            //  //Debug.Log("消耗的时间:" + sw.ElapsedMilliseconds);//以毫秒为单位

            // //Debug.Log("jsonRoot[0]:" + jsonRoot[0]);
            // //Debug.Log("jsonRoot[1]:" + jsonRoot[1]);
            // //Debug.Log("jsonRoot[2]:" + jsonRoot[2]);

            for (int i = 0; i < jsonRoot.Length; i++)
            {
                //  //Debug.Log("jsonRoot[0]:" + jsonRoot[i]);
                ListAndList listAndList = new ListAndList();
                jsonRoot[i] = jsonRoot[i].Replace("[[", "");
                jsonRoot[i] = jsonRoot[i].Replace("[", "");
                jsonRoot[i] = jsonRoot[i].Replace("]\r\n", "");
                jsonRoot[i] = jsonRoot[i].Replace("]", "");

                ////Debug.Log("jsonRoot[i]:" + jsonRoot[i]);
                string[] jsonEvery = jsonRoot[i].Split(',');
                ////Debug.Log("jsonEvery size:" + jsonEvery.Length);
                for (int j = 0; j < jsonEvery.Length; j++)
                {
                    string tmp = "";
                    tmp = jsonEvery[j].Replace("\"", "");
                    tmp = tmp.Replace("\"", "");
                    listAndList.AddList(tmp);
                }
                listRoot.Add(listAndList);
            }

            return (listRoot.Count <= 0) ? null : listRoot;
        }


    }
}
