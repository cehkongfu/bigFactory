package blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.Utils;

/**
 	题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 	AA打印5次，BB打印10次，CC打印15次
 	紧接着
 	AA打印5次，BB打印10次，CC打印15次
 	...
 	来10轮
**/

class ShareResource {
	int number = 1;
	private Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();
	public void print5() {
		lock.lock();
		try {
			//1、判断
			while(number!=1) {
				c1.await();
			}
			//2、干活
			for(int i=1; i<6; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			Utils.sleep(1);
			//3、通知
			number = 2;
			c2.signal();

		}catch(Exception e){e.printStackTrace();}finally {lock.unlock();}
	}
	public void print10() {
		lock.lock();
		try {
			//1、判断
			while(number!=2) {
				c2.await();
			}
			//2、干活
			for(int i=1; i<11; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			Utils.sleep(1);
			//3、通知
			number = 3;
			c3.signal();

		}catch(Exception e){e.printStackTrace();}finally {lock.unlock();}
	}
	public void print15() {
		lock.lock();
		try {
			//1、判断
			while(number!=3) {
				c3.await();
			}
			//2、干活
			for(int i=1; i<16; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			Utils.sleep(1);
			//3、通知
			number = 1;
			c1.signal();
		}catch(Exception e){e.printStackTrace();}finally {lock.unlock();}
	}	
}
public class SyncAndReentrantLockDemo {
	
	public static void main(String[] args) {
		ShareResource sr = new ShareResource();
		new Thread(()->{
			for(int i=0; i<10; i++) {
				sr.print5();
			}
		},"A").start();
		
		new Thread(()->{
			for(int i=0; i<10; i++) {
				sr.print10();
			}
		},"B").start();
		
		new Thread(()->{
			for(int i=0; i<10; i++) {
				sr.print15();
			}	
		},"C").start();
	}

}
