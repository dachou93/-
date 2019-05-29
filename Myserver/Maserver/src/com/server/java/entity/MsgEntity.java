package com.server.java.entity;

import io.netty.channel.Channel;

/**
 * 后台处理逻辑的核心实体类
 */
public class MsgEntity {
	private short cmdCode;//�����a
	private byte[] data;//protobuf�������w
	private Channel channel;

	public short getCmdCode() {
		return cmdCode;
	}

	public void setCmdCode(short cmdCode) {
		this.cmdCode = cmdCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public void sendMsgToClient() {
		if (!channel.isActive()) {
			System.out.println("�����Ѿ��Ͽ�");
			return;
		}
	}

}
