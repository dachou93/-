package test;

import com.ma.handler.GameInputHandler;
import com.ma.handler.GameStartHandler;
import com.ma.logicThread.HandleCmdRunnable;
import com.ma.queue.CommonQueue;
import com.ma.server.HandlerMap;
import com.ma.server.Server_Main;
import com.server.java.constants.CmdConstant;

public class test {

	public static void main(String[] args) {
		Server_Main server = null;
		initHandler();
		//����һ���߼��߳������������˹��������߼�
		 HandleCmdRunnable cmdRunnable=new HandleCmdRunnable(CommonQueue.getInstance());
		    Thread thread=new Thread(cmdRunnable);
		    thread.start();
		    System.out.println("�߳̿���");
	    server = new Server_Main(8081);
	    
	}
	
	
	public static void initHandler()
	{
		
		HandlerMap.registeHandler(CmdConstant.gameStart, new GameStartHandler());
		HandlerMap.registeHandler(CmdConstant.gameUpdate, new GameInputHandler());
		
	}
	
}
