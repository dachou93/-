package com.ma.queue;

import com.server.java.entity.MsgEntity;
//��Ϣ����
public class CommonQueue extends BaseQueue<MsgEntity> {

	private static final CommonQueue INSTANCE = new CommonQueue();

	private CommonQueue() {
	}

	public static CommonQueue getInstance() {
		return INSTANCE;
	}


}
