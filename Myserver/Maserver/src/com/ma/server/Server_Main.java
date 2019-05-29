package com.ma.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server_Main {

	private EventLoopGroup acceptorGroup = null;

	private EventLoopGroup clientGroup = null;

	private ServerBootstrap bootstrap = null;

	public Server_Main(int port) {
		init(port);
	}

	private void init(int port) {
		acceptorGroup = new NioEventLoopGroup(4);
		clientGroup = new NioEventLoopGroup();
		try {
			bootstrap = new ServerBootstrap();

			bootstrap.group(acceptorGroup, clientGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024).childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(new NettyServerInitializer());
			ChannelFuture future = bootstrap.bind(port).sync();
			System.out.println("服务器启动");
			future.channel().closeFuture().sync();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			release();
			System.out.println("服务器关闭");
		}

//		bootstrap.group(acceptorGroup, clientGroup).option(ChannelOption.SO_BACKLOG, 1024).childOption(ChannelOption.SO_KEEPALIVE, true).channel(NioServerSocketChannel.class)
//		.childHandler(new NettyServerInitializer());

	}


	public void release() {
		this.acceptorGroup.shutdownGracefully();
		this.clientGroup.shutdownGracefully();
	}

}
