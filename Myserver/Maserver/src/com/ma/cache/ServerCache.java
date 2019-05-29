package com.ma.cache;

import com.server.java.entity.MsgEntity;
import com.server.java.entity.PlayerEntity;

import gnu.trove.impl.sync.TSynchronizedObjectIntMap;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import io.netty.channel.Channel;

public class ServerCache {


	public final static TObjectIntMap<Channel> CHANNEL_PLAYER = new TSynchronizedObjectIntMap<Channel>(new TObjectIntHashMap<Channel>());
	public final static TIntObjectHashMap<PlayerEntity> PLAYERS_MAP = new TIntObjectHashMap<PlayerEntity>();
	public final static TObjectIntHashMap<String> NAME_MAPS = new TObjectIntHashMap<String>();

	public static int playerId = 1;


	public static void addNewPlayer(String name, Channel ch) {
		PlayerEntity pe = new PlayerEntity();
		pe.setName(name);
		pe.setPlayerId(++playerId);
		pe.setCh(ch);
		CHANNEL_PLAYER.put(ch, playerId);
		PLAYERS_MAP.put(pe.getPlayerId(), pe);
		NAME_MAPS.put(name, 1);
	}

	public static int get(Channel channel) {
		return CHANNEL_PLAYER.get(channel);
	}

	public static boolean CheckName(String name) {
		return NAME_MAPS.contains(name);
	}

	public static PlayerEntity getPlayerById(int playerId) {
		return PLAYERS_MAP.get(playerId);
	}

	public static void sendToAll(MsgEntity msgEntity) {
		Object[] players = PLAYERS_MAP.values();
		PlayerEntity pe = null;
		for (Object object : players) {
			pe = (PlayerEntity) object;
			if (pe.getCh().isWritable()) {
				pe.getCh().writeAndFlush(msgEntity);
			}
		}
	}
}
