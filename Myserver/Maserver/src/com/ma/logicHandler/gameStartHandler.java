package com.ma.logicHandler;

import java.util.List;

import com.google.protobuf.AbstractMessage.Builder;
import com.ma.game.game_room;
import com.ma.game.game_room_2;
import com.ma.logicThread.AbstractCmdRunnable;
import com.ma.logicThread.HandleCmdRunnable;
import com.ma.test.protobuf.testmsg;
import com.ma.tool.ProtobufMessageHelper;
import com.server.java.constants.CmdConstant;
import com.server.java.entity.MsgEntity;

public class gameStartHandler extends baseLogicHandler{

	@Override
	public void handleMsg(MsgEntity msgEntity, List<MsgEntity> commandList,AbstractCmdRunnable logicThread) {
		
//	 HandleCmdRunnable	t= (HandleCmdRunnable)logicThread;
//	 if(t.getChannelInRoom(msgEntity.getChannel())!=null)
//	 {
//		 System.out.println(msgEntity.getChannel().id().toString()+"已经开启一个房间");
//		 return;
//	 }
//	 game_room room=new game_room(500, msgEntity.getChannel(), System.currentTimeMillis());
//	 t.add_room(room);
//	 t.setChannelInRoom(msgEntity.getChannel(), room);
		
		
		 HandleCmdRunnable	t= (HandleCmdRunnable)logicThread;
		 if(t.getChannelInRoom2(msgEntity.getChannel())!=null)
		 {
			 System.out.println(msgEntity.getChannel().id().toString()+"已经开启一个房间");
			 return;
		 }
		 game_room_2 room=t.get_waiting_room(500, msgEntity.getChannel(), System.currentTimeMillis(),2);
		 int index=room.getPlayerNum()-1;//将玩家的索引告知玩家
		 Builder b = testmsg.newBuilder();
		 b.setField(ProtobufMessageHelper.getField(b.getDescriptorForType(), "message"), String.valueOf(index));
		 MsgEntity r=new MsgEntity();
		 r.setCmdCode(CmdConstant.testMessage);
		 r.setData(b.build().toByteArray());
		 r.setChannel(msgEntity.getChannel());
		 commandList.add(r);System.out.println("ff");
		 t.setChannelInRoom(msgEntity.getChannel(), room);
	}

}
