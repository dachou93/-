package com.ma.server;

import java.util.HashMap;
import java.util.Map;
import com.ma.handler.MyHandler;

public class HandlerMap {
 
	private static Map<Short, MyHandler> Handlers=new HashMap<Short, MyHandler>(); ;  
	  
	public static MyHandler getHandlersByType(short type) {  
	    return  Handlers.get(type);  
	}  
	
	
  public static void registeHandler(short code,MyHandler handler )
  {
	  Handlers.put(code, handler);
	  
  }
}
