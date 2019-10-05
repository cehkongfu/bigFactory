package blockqueue;

import java.util.concurrent.SynchronousQueue;

import util.Utils;

public class SynchronousQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		SynchronousQueue<String> sq = new SynchronousQueue<>();
		new Thread(()->{
			try {
				System.out.println( Thread.currentThread().getName()+"\t put 1" );
				sq.put("1");
				
				System.out.println( Thread.currentThread().getName()+"\t put 2" );
				sq.put("2");
				
				System.out.println( Thread.currentThread().getName()+"\t put 3" );
				sq.put("3");
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		},"AAA").start();
		
		new Thread(()->{
			try {
				Utils.sleep(1);
				System.out.println(Thread.currentThread().getName()+"\t"+sq.take());
				
				Utils.sleep(1);
				System.out.println(Thread.currentThread().getName()+"\t"+sq.take());
				
				Utils.sleep(1);
				System.out.println(Thread.currentThread().getName()+"\t"+sq.take());
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		},"BBB").start();
	}
}
