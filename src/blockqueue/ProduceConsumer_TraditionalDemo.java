package blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
题目：一个初始值为零的变量，两个线程对其交替操作，一个加1，一个减1，来5轮

1	线程	操作	资源类
2	判断	干活	

**/
//涉及到多线程，一定会有一个资源类
class ShareData {
	private int number = 0;
	private Lock lock =new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void increment() throws Exception {
		lock.lock();
		try {
		//1判断
		while(number!=0) {
			//等待、不能生产
			condition.await();
		}

		//2干活
		number++;
		System.out.println(Thread.currentThread().getName() +"\t"+number);

		//3通知唤醒
		condition.signalAll();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws Exception {
		lock.lock();
		try {
		//1判断
		while(number==0) {
			//等待、不能生产
			condition.await();
		}

		//2干活
		number--;
		System.out.println(Thread.currentThread().getName() +"\t"+number);

		//3通知唤醒
		condition.signalAll();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
public class ProduceConsumer_TraditionalDemo {
	public static void main(String[] args) {

		ShareData shareData = new ShareData();
		for(int k=1; k<4; k++) {
			new Thread(()->{
				for(int i=0; i<5; i++) {
					try {
						shareData.increment();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			},"Producer"+k).start();
		}
		
		for(int k=1; k<4; k++) {
			new Thread(()->{
				for(int i=0; i<5; i++) {
					try {
						shareData.decrement();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			},"Consumer"+k).start();
		}
		
	}
}
