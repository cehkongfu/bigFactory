package someLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 可重入锁（也叫递归锁）：
 指的是同一线程外层获取锁之后，内层递归函数仍然能够获取该锁的代码
 同一个线程在外层方法获取锁的时候，在进入内层方法（拥有同样的锁）会自动解锁
 
 也就是说，线程可以进入任何一个他已经拥有的锁的同步的代码块
 
 case one:synchronized 是可重入锁
 case two:ReentrantLock 同样是可重入锁
 */
class Phone implements Runnable {
	public synchronized void sendSMS() throws Exception {
		System.out.println(Thread.currentThread().getName() +"\t invoked sendSMS");
		sendEmail();
	}
	public synchronized void sendEmail() throws Exception {
		System.out.println(Thread.currentThread().getName() +"\t invoked sendEmail");
	}
	
	///
	ReentrantLock lock = new ReentrantLock();
	public void run() {
		get();
	}
	
	public void set() {
		lock.lock();
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() +"\t invoked set");
		}finally {
			lock.unlock();
			lock.unlock();
		}
	}
	public void get() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() +"\t invoked get");
			set();
		}finally {
			lock.unlock();
		}
	}
}
public class ReenterLockDemo {
	public static void main(String[] args) throws Exception {
		Phone phone = new Phone();
		System.out.println("case one:验证synchronized同样是可重入锁");
		new Thread(()->{
			try {
				phone.sendSMS();
			}catch(Exception e) {}
		}, "T1").start();
		new Thread(()->{
			try {
				phone.sendSMS();
			}catch(Exception e) {}
		}, "T2").start();
		
		try { TimeUnit.SECONDS.sleep(1); } catch (Exception e) { }
		System.out.println();
		System.out.println();
		System.out.println("case two:验证ReentrantLock同样是可重入锁");
		new Thread(phone, "T3").start();
		new Thread(phone, "T4").start();
	}
}
