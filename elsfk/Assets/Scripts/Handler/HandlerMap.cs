using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HandlerMap 
{
    private static Dictionary<short, MyHandler> Handlers = new Dictionary<short, MyHandler>(); 
	  
	public static MyHandler getHandlersByType(short type)
    {
        return Handlers[type];
    }


    public static void registeHandler(short code, MyHandler handler)
    {
        Handlers.Add(code, handler);
    }
}

