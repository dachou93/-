package com.ma.logicThread;

import java.util.HashMap;
import java.util.Map;


import com.server.java.entity.MsgEntity;
import com.ma.logicHandler.baseLogicHandler;
import com.ma.queue.BaseQueue;

public abstract class AbstractCmdRunnable implements Runnable {
	private BaseQueue<MsgEntity> INSTANCE;
	protected static Map<Short, baseLogicHandler> handlerMap;

	public AbstractCmdRunnable(BaseQueue<MsgEntity> INSTANCE) {
		this.INSTANCE = INSTANCE;
		handlerMap=new  HashMap<Short, baseLogicHandler>();
		logicMapInit();
	}
	
	

	@Override
	public void run() {
//		for (;;) {
//			try {
//				System.out.println(INSTANCE.getQueueSize());
				MsgEntity msgEntity = null;
				while (INSTANCE.getQueueSize() > 0) {
					msgEntity = INSTANCE.take();
					if (msgEntity != null) {
						handleMsg(msgEntity);
					}
				}
//				TimeUnit.MILLISECONDS.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public abstract void handleMsg(MsgEntity msgEntity);
	
	public abstract void logicMapInit();


}
