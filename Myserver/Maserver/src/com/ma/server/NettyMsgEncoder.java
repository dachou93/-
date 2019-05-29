package com.ma.server;

import com.server.java.entity.MsgEntity;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyMsgEncoder extends MessageToByteEncoder<MsgEntity> {

	@Override
	protected void encode(ChannelHandlerContext ctx,MsgEntity msg, ByteBuf out) throws Exception {
		int dataLength = msg.getData() == null ? 0 : msg.getData().length;
		out.ensureWritable(4 + dataLength);
		out.writeInt(dataLength);
		out.writeShort(msg.getCmdCode());
		if (dataLength > 0) {
			out.writeBytes(msg.getData());
		}
	}

}
