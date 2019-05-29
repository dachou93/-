package com.ma.logicHandler;

import java.util.List;

import com.ma.logicThread.AbstractCmdRunnable;
import com.server.java.entity.MsgEntity;

public abstract class baseLogicHandler {
	
		public abstract void handleMsg(MsgEntity msgEntity, List<MsgEntity> commandList,AbstractCmdRunnable logicThread);
}
