package com.ma.server;

import com.server.java.entity.MsgEntity;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class MsgEntityHanlder extends SimpleChannelInboundHandler<MsgEntity> {

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, MsgEntity arg1) throws Exception {
		System.out.println("handler");
		if(null==arg1)
		{
			return;
		}
		HandlerMap.getHandlersByType(arg1.getCmdCode()).doHandler(arg0,arg1);;
		
	}
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	cause.printStackTrace();
	ctx.close();
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		System.out.println("client"+ctx.channel().id()+"½øÈë");
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
		System.out.println("client"+ctx.channel().id()+"ÍË³ö");
	}

}
