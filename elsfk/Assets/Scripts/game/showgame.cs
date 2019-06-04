using UnityEngine;
using UnityEditor;
using System.Collections.Generic;

public class showgame 
{
    private Dictionary<string, List<GameObject>> cache = new Dictionary<string, List<GameObject>>();//方块对象池
    private Transform[,] inputoutput_transforms = new Transform[10, 20];//存放小方块数组
    private Transform[,] inputoutput_transforms2 = new Transform[10, 20];
    private GameObject inputoutput_prefab_gameobject;
    private Sprite[] sps;
    public Texture2D sp;

    public showgame()
    {
        sp = LoadResources.sp;
        Sprite sprite = Sprite.Create(sp, new Rect(0, 0, 80, 80), new Vector2(0.5f, 0.5f));
        inputoutput_prefab_gameobject = new GameObject();
        var ke = inputoutput_prefab_gameobject.AddComponent<SpriteRenderer>();
        ke.sprite = sprite;
        inputoutput_prefab_gameobject.SetActive(false);
    }

    public void elsfk_input_drawgame(int[,] map,int[,] map2)
    {
        //回收所有方块
        for (int y = 0; y < 20; y++)
        {
            for (int x = 0; x <10; x++)
            {
                if (inputoutput_transforms[x, y] != null)
                {
                    intput_collectObject(inputoutput_transforms[x, y].gameObject);
                    inputoutput_transforms[x, y] = null;
                 
                }
                if (inputoutput_transforms2[x, y] != null)
                {
                    intput_collectObject(inputoutput_transforms2[x, y].gameObject);
                    inputoutput_transforms2[x, y] = null;
                }
            }
        }
        for (int y = 0; y < 20; y++)
        {
            for (int x = 0; x < 10; x++)
            {
                //通过对象池创建方块
                if ( map[x, y] != 0)
                {

                    GameObject fa = intput_createObject(inputoutput_prefab_gameobject.name, inputoutput_prefab_gameobject, new Vector2(x+(-5*test.me),y), Quaternion.identity);
                    inputoutput_transforms[x, y] = fa.transform;

                    
                }
                if (map2[x, y] != 0)
                {
                    GameObject fa2 = intput_createObject(inputoutput_prefab_gameobject.name, inputoutput_prefab_gameobject, new Vector2(x + (5 * test.me), y), Quaternion.identity);
                    inputoutput_transforms2[x, y] = fa2.transform;
                }

            }
        }
    }

    public GameObject intput_createObject(string key, GameObject go, Vector3 pos, Quaternion dir)
    {
        //现在池中查找是否存在可用对象 
        GameObject targetObject = intput_findusableobject(key);
        //如果不存在，则创建新对象，加入池中  
        if (targetObject == null)
        {
            targetObject = GameObject.Instantiate<GameObject>(go);

            intput_addObject(key, targetObject);
        }
        //使用对象
        intput_useObject(pos, dir, targetObject);
        return targetObject;
    }

    /// <summary>
    /// 设置对象位置并显示
    /// </summary>
    /// <param name="pos">位置</param>
    /// <param name="dir">方向</param>
    /// <param name="targetObject">目标物体</param>
    /// <returns></returns>
    private static void intput_useObject(Vector3 pos, Quaternion dir, GameObject targetObject)
    {
        targetObject.transform.position = pos;
        targetObject.transform.rotation = dir;
        targetObject.SetActive(true);
    }
    /// <summary>
    /// 将对象存入池中
    /// </summary>
    /// <param name="key">键</param>
    /// <param name="targetObject">游戏对象</param>
    private void intput_addObject(string key, GameObject targetObject)
    {
        //如果不存在当前键，则添加记录
        if (!cache.ContainsKey(key)) cache.Add(key, new List<GameObject>());
        cache[key].Add(targetObject);
    }
    /// <summary>
    /// 按所给键从池中取游戏对象
    /// </summary>
    /// <param name="key">键</param> 
    private GameObject intput_findusableobject(string key)
    {
        return cache.ContainsKey(key) ? cache[key].Find(o => !o.activeInHierarchy) : null;
    }

    /// <summary>
    /// 即时回收
    /// </summary>
    /// <param name="go"></param>
    public void intput_collectObject(GameObject go)
    {
        go.SetActive(false);
    }
    /// <summary>
    /// 清空一类游戏对象
    /// </summary>
    /// <param name="key"></param>
    public void intput_clear(string key)
    {
        //销毁列表中的游戏对象
        foreach (var item in cache[key])
        {
            GameObject.Destroy(item);//没有立即销毁
        }
        //移除记录
        cache.Remove(key);
    }

    /// <summary>
    /// 清空所有
    /// </summary>
    public void intput_clearAll()
    {
        //将字典所有键存入列表集合
        List<string> keys = new List<string>(cache.Keys);
        //遍历列表
        foreach (string item in keys)
        {
            //删除字典记录 
            intput_clear(item);
        }
    }
}