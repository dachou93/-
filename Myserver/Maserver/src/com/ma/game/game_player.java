package com.ma.game;

import java.util.List;

import io.netty.channel.Channel;

public class game_player {
	private game_manager manager;

	private game_map map;


	private Channel channel;
	
	private int index;

	public game_manager getManager() {
		return manager;
	}

	public void setManager(game_manager manager) {
		this.manager = manager;
	}

	public game_map getMap() {
		return map;
	}

	public void setMap(game_map map) {
		this.map = map;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public game_player(int in ,Channel c)
	{
		manager = new game_manager();
		map = new game_map();
		manager.Init(game_define.DEFPOSX, game_define.DEFPOSY, map);
		channel = c;
		index=in;
	}
	
	
	public  List<Integer> get_map() {
		return map.getMap();
	}
	
	
	
	


}
