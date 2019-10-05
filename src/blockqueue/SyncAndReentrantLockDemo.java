package blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.Utils;

/**
 	��Ŀ�����߳�֮�䰴˳����ã�ʵ��A->B->C�����߳�������Ҫ�����£�
 	AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15��
 	������
 	AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15��
 	...
 	��10��
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
			//1���ж�
			while(number!=1) {
				c1.await();
			}
			//2���ɻ�
			for(int i=1; i<6; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			Utils.sleep(1);
			//3��֪ͨ
			number = 2;
			c2.signal();

		}catch(Exception e){e.printStackTrace();}finally {lock.unlock();}
	}
	public void print10() {
		lock.lock();
		try {
			//1���ж�
			while(number!=2) {
				c2.await();
			}
			//2���ɻ�
			for(int i=1; i<11; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			Utils.sleep(1);
			//3��֪ͨ
			number = 3;
			c3.signal();

		}catch(Exception e){e.printStackTrace();}finally {lock.unlock();}
	}
	public void print15() {
		lock.lock();
		try {
			//1���ж�
			while(number!=3) {
				c3.await();
			}
			//2���ɻ�
			for(int i=1; i<16; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			Utils.sleep(1);
			//3��֪ͨ
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
