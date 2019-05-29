package com.ma.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class BaseQueue<T> {


	private final BlockingQueue<T> queue = new LinkedBlockingQueue<T>();//该队列的put方法在队列满的时候会阻塞


	public T take() {
		return queue.poll();
	}


	public void put(T t) {
		try {
			queue.put(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getQueueSize() {
		return queue.size();
	}

}
