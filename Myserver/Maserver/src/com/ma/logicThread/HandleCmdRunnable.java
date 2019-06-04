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
import com.ma.game.game_room_2;
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
	
	private List<game_room_2> rooms2=new ArrayList<game_room_2>();
	private Map<Channel, game_room_2> playerRoom2 = new HashMap<Channel, game_room_2>();

	
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
//				System.out.println("房间数量为："+rooms2.size());
				if (rooms2.size() > 0) {
					
					Iterator<game_room_2> it = rooms2.iterator();
					while(it.hasNext()){
						game_room_2 r = it.next();
					   if(r.getGameState()==2)
					   {
						   it.remove();
						   List<Channel> cs=r.get_all_channels();
						   for(Channel c:cs)
						   {
						   playerRoom2.remove(c);
						   }
						  
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
	private void add_room2(game_room_2 room2) {
		rooms2.add(room2);
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
	
	public game_room_2 getChannelInRoom2(Channel c) {
		return playerRoom2.get(c);
	}

	
	public game_room_2 get_waiting_room(long freshTime,Channel c,long sysTime,int number) {
		game_room_2 gg=null;
		for(game_room_2 g:rooms2)
		{
			if(g.getGameState()==0)
				gg=g;
		}
		if(null==gg)
		{
			gg=new game_room_2(freshTime, c, sysTime, number);
			add_room2(gg);
		}
		gg.joinRoom(c);
		return gg;
	}
	
	public void setChannelInRoom(Channel c, game_room_2 room2) {
		playerRoom2.put(c, room2);
	}
}
