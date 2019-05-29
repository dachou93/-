package com.ma.logicThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.server.java.constants.CmdConstant;
import com.server.java.entity.MsgEntity;

import io.netty.channel.Channel;

import com.ma.game.game_room;
import com.ma.logicHandler.gameInputHandler;
import com.ma.logicHandler.gameStartHandler;
import com.ma.queue.BaseQueue;

public class HandleCmdRunnable extends AbstractCmdRunnable {

	public HandleCmdRunnable(BaseQueue<MsgEntity> INSTANCE) {
		super(INSTANCE);
		logicMapInit();
	}

	private List<game_room> rooms = new ArrayList<game_room>();

	private Map<Channel, game_room> playerRoom = new HashMap<Channel, game_room>();

	
	@Override
	public void handleMsg(MsgEntity msgEntity) {
		List<MsgEntity> commandList = new ArrayList<MsgEntity>();
		try {
			handlerMap.get(msgEntity.getCmdCode()).handleMsg(msgEntity, commandList, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (commandList != null && commandList.size() > 0) {
			for (MsgEntity tempMessage : commandList) {
				tempMessage.getChannel().writeAndFlush(tempMessage);
			}
		}
		commandList.clear();
		commandList = null;
	}

	@Override
	public void logicMapInit() {
		handlerMap.put(CmdConstant.gameStart, new gameStartHandler());
		handlerMap.put(CmdConstant.gameUpdate, new gameInputHandler());
	}

	@Override
	public void run() {
		for (;;) {
			try {
				super.run();
				if (rooms.size() > 0) {
					
					Iterator<game_room> it = rooms.iterator();
					while(it.hasNext()){
						game_room r = it.next();
					   if(r.getGameState()==1)
					   {
						   it.remove();
						   playerRoom.remove(r.getChannel());
						   continue;
					   }
					   r.run(System.currentTimeMillis());
					}
				}

				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void add_room(game_room room) {
		rooms.add(room);
	}

	public void setChannelInRoom(Channel c, game_room room) {
		playerRoom.put(c, room);
	}

	public void removeChannel(Channel c) {
		playerRoom.remove(c);
	}

	public game_room getChannelInRoom(Channel c) {
		return playerRoom.get(c);
	}

}
