package com.ma.logicHandler;

import java.util.List;

import com.ma.game.game_room;
import com.ma.logicThread.AbstractCmdRunnable;
import com.ma.logicThread.HandleCmdRunnable;
import com.server.java.entity.MsgEntity;

public class gameStartHandler extends baseLogicHandler{

	@Override
	public void handleMsg(MsgEntity msgEntity, List<MsgEntity> commandList,AbstractCmdRunnable logicThread) {
		
	 HandleCmdRunnable	t= (HandleCmdRunnable)logicThread;
	 game_room room=new game_room(500, msgEntity.getChannel(), System.currentTimeMillis());
	 t.add_room(room);
	 t.setChannelInRoom(msgEntity.getChannel(), room);
	}

}
