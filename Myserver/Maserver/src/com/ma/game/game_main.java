package com.ma.game;

import java.util.List;

import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Descriptors;
import com.ma.test.protobuf.gamedata;
import com.server.java.constants.CmdConstant;
import com.server.java.entity.MsgEntity;

import io.netty.channel.Channel;

public class game_main implements Runnable {

	private game_manager manager;

	private game_map map;

	private boolean flag;

	private Channel channel;

	long time;
	public game_main(Channel c) {
		manager = new game_manager();
		map = new game_map();
		manager.Init(game_define.DEFPOSX, game_define.DEFPOSY, map);
		channel = c;
		time = System.currentTimeMillis();
		flag = true;
	}

	public synchronized void run() {
		try {
			while (flag) {
				auto_down();
				sendMessage(channel, CmdConstant.gameUpdate, build_game_data(get_map()));
				wait(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// 当有输入主动调用案件相应
	public synchronized void setClicktype(int clicktype) {

		manager.OnClickkeyboard(clicktype);
		sendMessage(channel, CmdConstant.gameUpdate, build_game_data(get_map()));
	}

	public synchronized List<Integer> get_map() {
		return map.getMap();
	}

	private void auto_down() {

		long temp = System.currentTimeMillis();
		if (temp - time >= 1000) {
			time = temp;
			manager.OnClickkeyboard(game_define.STATE_DOWN);
		}

	}

	public void sendMessage(Channel c, short code, byte[] msg) {
		if (!c.isActive()) {
			System.out.println("玩家不在线");
			flag = false;
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

}
