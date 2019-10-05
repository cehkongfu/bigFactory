package someLock;

import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {
	public synchronized void sendSMS() throws Exception {
		System.out.println(Thread.currentThread().getId() +"\t invoked sendSMS");
	}
	public synchronized void sendEmail() throws Exception {
		System.out.println(Thread.currentThread().getId() +"\t invoked sendEmail");
	}
	
	///
	ReentrantLock lock = new ReentrantLock();
	public void run() {
		get();
	}
	
	public void get() {
		lock.lock();
	}
}
public class ReenterLockDemo {
	public static void main(String[] args) throws Exception {
		Phone phone = new Phone();
		new Thread(()->{
			
		}, "T1").start();
	}
}
