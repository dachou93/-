package com.server.java.entity;

import io.netty.channel.Channel;

/**
 * 鍚庡彴澶勭悊閫昏緫鐨勬牳蹇冨疄浣撶被
 */
public class MsgEntity {
	private short cmdCode;//操作碼
	private byte[] data;//protobuf數據主體
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
			System.out.println("连接已经断开");
			return;
		}
	}

}
