package com.lym.test;

import com.lym.logger.Logger;

public class TestThreadLogger implements Runnable{

	int id;
	Logger logger;
	
	public void setId(int i){
		id = i;
	}
	public void setLogger(Logger l){
		logger = l;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 10000; i++){
			logger.debug("thread " + id + "is running " + i +"time(s)");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args) {
		Logger mylogger = Logger.getInstance();
		mylogger.setLogpath("testloger.log");
		TestThreadLogger ttl[]  = new TestThreadLogger[1000];
		for(int i =0; i<1000; i++){
			ttl[i]=new TestThreadLogger();
			ttl[i].setId(i);
			ttl[i].setLogger(mylogger);
		}
		for(int i=0;i<1000;i++){
			Thread t = new Thread(ttl[i]);
			mylogger.debug("thread " + i + "before start");
			t.start();
			mylogger.debug("thread " +i+ "finish start");
		}
	}
}
