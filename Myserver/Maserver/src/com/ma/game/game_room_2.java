package com.ma.game;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.Descriptors;
import com.google.protobuf.AbstractMessage.Builder;
import com.ma.test.protobuf.gamedata;
import com.ma.test.protobuf.gamedata2;
import com.ma.test.protobuf.testmsg;
import com.ma.test.protobuf.gamedata2.gamedata1;
import com.server.java.constants.CmdConstant;
import com.server.java.entity.MsgEntity;

import io.netty.channel.Channel;

public class game_room_2 {

	private game_player[] players;

	private long lastfresh;

	private long freshTime;

	private long lastdown;

	private int gameState;// 0玩家等待， 1游戲開始 2，游戲結束

	private int playerNum;// 記錄當前人數

	private int maxNum;

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	

	public long getLastfresh() {
		return lastfresh;
	}

	public void setLastfresh(long lastfresh) {
		this.lastfresh = lastfresh;
	}

	public long getFreshTime() {
		return freshTime;
	}

	public void setFreshTime(long freshTime) {
		this.freshTime = freshTime;
	}

	public long getLastdown() {
		return lastdown;
	}

	public void setLastdown(long lastdown) {
		this.lastdown = lastdown;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public game_room_2(long freshTime, Channel c, long sysTime, int number) {
		players=new game_player[number];
//		manager.Init(game_define.DEFPOSX, game_define.DEFPOSY, map);
		lastfresh = sysTime;
		lastdown = lastfresh;
		this.freshTime = freshTime;
		gameState = 0;
		maxNum = number;
		playerNum = 0;
//		joinRoom(c);
	}

	public void joinRoom(Channel c) {

		players[playerNum] = new game_player(playerNum,c);
		playerNum++;
		if(playerNum==maxNum)
			gameState=1;

	}

	public void run(long sysTime) {

		//等待状态
		if(gameState==0)
		{
			for(game_player g:players)
			{
				if(g==null)
					continue;
				if(!g.getChannel().isActive())
					gameState=2;
			}
			return;
		}
		
		//游戏结束
		if(gameState==2)
		{
			return;
		}
		
		
		//游戏运行中
		long temp=lastfresh-sysTime;
		if(!(temp<=0))
		{
			return;
		}
		lastfresh=sysTime+freshTime;
		boolean flag=false;//玩家是否都在线
		boolean flag2=true;//玩家是否游戏都结束了
		for(game_player g:players)
		{
			if(g.getChannel().isActive())
			{
				flag=true;
			}
			if(!g.getManager().isGameOver())
			{
				flag2=false;
			}
			
		}
		if(!flag)
		{
			gameState=2;
			return;
		}
		if(flag2)
		{
			gameState=2;
			sendToAllPlayers(CmdConstant.testMessage, build_game_over_data());
		    return;
		}
		
		
		auto_down(sysTime);

		sendToAllPlayers(CmdConstant.gameUpdate,build_game_data(getdatas()));
	}

	private void auto_down(long current) {

		if (current - lastdown >= 1000) {
			lastdown = current;
			for (game_player  player:  players) {
				player.getManager().OnClickkeyboard(game_define.STATE_DOWN);
			}
		}

	}
	
	//获取所有玩家map信息
	private List<gamedata1> getdatas()
	{
		
		List<gamedata1> datasList=new ArrayList<gamedata1>();
		for(int i=0;i<maxNum;i++)
		{
			Builder b = gamedata1.newBuilder();
			b.setField(getField(b.getDescriptorForType(), "data"), players[i].get_map());
			
			datasList.add((gamedata1)(b.build()));
		}
		return datasList;
	}
	//构建所有玩家地图消息
	private byte[] build_game_data(List<gamedata1> ds) {
		Builder b = gamedata2.newBuilder();
		b.setField(getField(b.getDescriptorForType(), "datas"), ds);
		return b.build().toByteArray();
	}
	
	private static Descriptors.FieldDescriptor getField(Descriptors.Descriptor descriptor, String name) {
		return descriptor.findFieldByName(name);
	}
	
	public void sendMessage(Channel c, MsgEntity msg) {
		if (!c.isActive()) {
			System.out.println("channelisnotactive");
			return;
		}
		c.writeAndFlush(msg);
	}
	
	public MsgEntity creatMsg(short code, byte[] msg) {
		MsgEntity msgEntity = new MsgEntity();
		msgEntity.setCmdCode(code);
		msgEntity.setData(msg);
		return msgEntity;
	}

	public void sendMessage(Channel c, short code, byte[] msg) {
		if (!c.isActive()) {
			System.out.println("channelisnotactive");
			return;
		}
		MsgEntity msgEntity = new MsgEntity();
		msgEntity.setCmdCode(code);
		msgEntity.setData(msg);
		sendMessage(c, msgEntity);
	}
	
	public List<Channel> get_all_channels()
	{
		List<Channel>channels =new ArrayList<Channel>();
		for(game_player p: players)
		{
			if(p!=null)
			channels.add(p.getChannel());
		}
		return channels;
	}
	
	public void sendToAllPlayers(short code, byte[] msg) {
		MsgEntity msge=new MsgEntity();
		msge.setCmdCode(code);
		msge.setData(msg);
		for(game_player p:players)
		{
			sendMessage(p.getChannel(), msge);
		}
	}
	game_player get_player_by_channel(Channel c)
	{
		for(game_player g:players)
		{
			if(g.getChannel()==c)
				return g;
		}
		return null;
	}
	
	public void setClicktype(int clicktype,Channel c) {

		game_player g=get_player_by_channel(c);
		if(g==null)
			return;
		
		g.getManager().OnClickkeyboard(clicktype);
		sendToAllPlayers(CmdConstant.gameUpdate,build_game_data(getdatas()));
//		sendMessage(channel, CmdConstant.gameUpdate, build_game_data(get_map()));
	}
	
	
	private byte[] build_game_over_data() {
		Builder b = testmsg.newBuilder();
		return b.build().toByteArray();
	}
}
