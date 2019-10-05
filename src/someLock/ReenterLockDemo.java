package someLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 ����������Ҳ�еݹ�������
 ָ����ͬһ�߳�����ȡ��֮���ڲ�ݹ麯����Ȼ�ܹ���ȡ�����Ĵ���
 ͬһ���߳�����㷽����ȡ����ʱ���ڽ����ڲ㷽����ӵ��ͬ�����������Զ�����
 
 Ҳ����˵���߳̿��Խ����κ�һ�����Ѿ�ӵ�е�����ͬ���Ĵ����
 
 case one:synchronized �ǿ�������
 case two:ReentrantLock ͬ���ǿ�������
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
		System.out.println("case one:��֤synchronizedͬ���ǿ�������");
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
		System.out.println("case two:��֤ReentrantLockͬ���ǿ�������");
		new Thread(phone, "T3").start();
		new Thread(phone, "T4").start();
	}
}
