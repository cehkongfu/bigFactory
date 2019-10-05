package someLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
	AtomicReference af = new AtomicReference();
	
	private void myLock() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName()+"线程coming...");
		while(!af.compareAndSet(null, thread)) {}
	}
	
	public void myUnlock() {
		Thread thread = Thread.currentThread();
		af.compareAndSet(thread, null);
		System.out.println(thread.getName()+"线程out...");
	}
	public static void main(String[] args) {
		SpinLockDemo spinLock = new SpinLockDemo();
		new Thread(()->{
			spinLock.myLock();
			try { TimeUnit.SECONDS.sleep(5); } catch (Exception e) { e.printStackTrace(); }
			System.out.println(Thread.currentThread().getName()+"线程跑5秒之后...");
			spinLock.myUnlock();
		}).start();

		new Thread(()->{
			spinLock.myLock();
			try { TimeUnit.SECONDS.sleep(3); } catch (Exception e) { e.printStackTrace(); }
			spinLock.myUnlock();
		}).start();
	}

}
