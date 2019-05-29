package com.ma.handler;

import com.server.java.entity.MsgEntity;

import io.netty.channel.ChannelHandlerContext;



//只用作接受后存入对于消息队列
public abstract  class MyHandler {
	 
	public abstract void doHandler(ChannelHandlerContext arg0,  MsgEntity arg1);
	
	
}
