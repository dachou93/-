package com.ma.game;

import java.util.List;

import com.google.protobuf.Descriptors;
import com.google.protobuf.AbstractMessage.Builder;
import com.ma.test.protobuf.gamedata;
import com.ma.test.protobuf.testmsg;
import com.server.java.constants.CmdConstant;
import com.server.java.entity.MsgEntity;

import io.netty.channel.Channel;

public class game_room {
  
	
	private game_manager manager;

	private game_map map;


	private Channel channel;
	
	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	private long lastfresh;
	
	private long freshTime;
	
	private long lastdown;
	
	private int gameState;//0玩家在，1玩家不在
	public game_room(long freshTime,Channel c ,long sysTime)
	{
		manager = new game_manager();
		map = new game_map();
		manager.Init(game_define.DEFPOSX, game_define.DEFPOSY, map);
		channel = c;
		lastfresh = sysTime;
		lastdown=lastfresh;
		this.freshTime=freshTime;
		gameState=0;
	}
	
	
	public  void run(long sysTime) {
		if(manager.isGameOver()==true)
		{
			gameState=1;
			sendMessage(channel,CmdConstant.testMessage,  build_game_over_data());
			return;
		}
		long temp=lastfresh-sysTime;
		if(!(temp<=0))
		{
			return;
		}
		lastfresh=sysTime+freshTime;
		auto_down(sysTime);
		sendMessage(channel, CmdConstant.gameUpdate, build_game_data(get_map()));
	
	}


	public void setClicktype(int clicktype) {

		manager.OnClickkeyboard(clicktype);
		sendMessage(channel, CmdConstant.gameUpdate, build_game_data(get_map()));
	}

	public  List<Integer> get_map() {
		return map.getMap();
	}

	private void auto_down(long current) {

		if (current - lastdown >= 1000) {
			lastdown=current;
			manager.OnClickkeyboard(game_define.STATE_DOWN);
		}

	}
	
	
	public MsgEntity creatMsg(Channel c, short code, byte[] msg) {
		MsgEntity msgEntity = new MsgEntity();
		msgEntity.setCmdCode(code);
		msgEntity.setData(msg);
		return msgEntity;
	}

	public void sendMessage(Channel c, short code, byte[] msg) {
		if (!c.isActive()) {
			System.out.println("channelisnotactive");
			gameState=1;
			return;
		}
		MsgEntity msgEntity = new MsgEntity();
		msgEntity.setCmdCode(code);
		msgEntity.setData(msg);
		sendMessage(c, msgEntity);
	}

	public void sendMessage(Channel c, MsgEntity msg) {
		c.writeAndFlush(msg);
	}

	private static Descriptors.FieldDescriptor getField(Descriptors.Descriptor descriptor, String name) {
		return descriptor.findFieldByName(name);
	}

	private byte[] build_game_data(List<Integer> d) {
		Builder b = gamedata.newBuilder();
		b.setField(getField(b.getDescriptorForType(), "row"), game_globalData.g_elsfk_globalData_hight);
		b.setField(getField(b.getDescriptorForType(), "column"), game_globalData.g_elsfk_globalData_width);
		b.setField(getField(b.getDescriptorForType(), "data"), d);
		return b.build().toByteArray();
	}
	
	private byte[] build_game_over_data() {
		Builder b = testmsg.newBuilder();
		return b.build().toByteArray();
	}


	public int getGameState() {
		return gameState;
	}


	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	
	
	
}
