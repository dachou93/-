package com.ma.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class NettyServerInitializer extends  ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		arg0.pipeline().addLast("decoder", new NettyMsgDecoder())
		.addLast("encoder", new NettyMsgEncoder())
		.addLast("handler", new MsgEntityHanlder() );	
		
	}

}
