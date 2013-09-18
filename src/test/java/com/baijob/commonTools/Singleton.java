package com.baijob.commonTools;

import com.baijob.commonTools.thread.Executor;

public class Singleton {

	private static Singleton instance = null;

	public Singleton() {
		System.out.println("Singleton instancing...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		System.out.println("Singleton instanced!");
	}

	private static class SingletonHolder {
		public final static Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}

	public static Singleton getSingleInstance(){  
		if(null == instance ) {
			synchronized(Singleton.class){
				if(null == instance) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	public static void other() {
		System.out.println("Do other things");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Executor.execute(new Runnable(){

				@Override
				public void run() {
					Singleton.getSingleInstance();
					System.out.println("Thread complate");
				}
			});
		}
	}
}
