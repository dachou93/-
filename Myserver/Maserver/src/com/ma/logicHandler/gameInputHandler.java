package com.ma.logicHandler;

import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ma.game.game_room;
import com.ma.game.game_room_2;
import com.ma.logicThread.AbstractCmdRunnable;
import com.ma.logicThread.HandleCmdRunnable;
import com.ma.test.protobuf.gameinput;
import com.server.java.entity.MsgEntity;

public class gameInputHandler extends baseLogicHandler {

	@Override
	public void handleMsg(MsgEntity msgEntity, List<MsgEntity> commandList, AbstractCmdRunnable logicThread) {
		HandleCmdRunnable	t= (HandleCmdRunnable)logicThread;
		game_room_2 room= t.getChannelInRoom2(msgEntity.getChannel());
		if(room==null)
			return;
		gameinput m=getMsg(msgEntity);
		room.setClicktype(m.getCode(),msgEntity.getChannel());
		System.out.println(msgEntity.getChannel().id().toString()+"按下按键类型"+m.getCode());
		
		
	}
	
	
	protected gameinput getMsg(MsgEntity arg1) {
		try {
			gameinput t = gameinput.parseFrom(arg1.getData());
			return t;
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return null;
	}

}
