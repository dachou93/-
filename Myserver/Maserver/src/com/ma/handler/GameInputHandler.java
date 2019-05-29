package com.ma.handler;

import com.ma.queue.CommonQueue;
import com.server.java.entity.MsgEntity;

import io.netty.channel.ChannelHandlerContext;

public class GameInputHandler extends MyHandler{



	@Override
	public void doHandler(ChannelHandlerContext arg0, MsgEntity arg1) {
		CommonQueue.getInstance().put(arg1);
	}

}
