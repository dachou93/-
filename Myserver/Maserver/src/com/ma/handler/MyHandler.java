package com.ma.handler;

import com.server.java.entity.MsgEntity;

import io.netty.channel.ChannelHandlerContext;



//ֻ�������ܺ���������Ϣ����
public abstract  class MyHandler {
	 
	public abstract void doHandler(ChannelHandlerContext arg0,  MsgEntity arg1);
	
	
}
