package someLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 	多个线程同时读一个资源，一点问题没有，所以为了提高并发量，多个线程同时读取共享资源没有问题
 	对于写操作就不一样了，不允许多个线程同时进行写操作
 	
 	读-读 可以共享
 	读-写 不能共享
 	写-写 可以共享
 */
class MyCache{
	private volatile Map<String, Object> map = new HashMap<>();
	private ReentrantReadWriteLock wrlock = new ReentrantReadWriteLock();

	public void put(String key, Object value) {
		wrlock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 正在写入：" + key);
			try {TimeUnit.MILLISECONDS.sleep(300);} catch(InterruptedException e) {e.printStackTrace();}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"\t 写入完成:");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			wrlock.writeLock().unlock();
		}
	}
	public void get(String key) {
		wrlock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 正在读出：" + key);
			try {TimeUnit.MILLISECONDS.sleep(300);} catch(InterruptedException e) {e.printStackTrace();}
			map.get(key);
			System.out.println(Thread.currentThread().getName()+"\t 读出完成:");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			wrlock.readLock().unlock();
		}
	}
}
public class ReadWriteLockDemo {
	
	
	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		for(int i=1; i<=5; i++) {
			final int tempInt = i;
			new Thread(()->{
				myCache.put(tempInt+"", tempInt+"");
			}, "Thread"+i).start();
		}

		for(int i=1; i<=5; i++) {
			final int tempInt = i;
			new Thread(()->{
				myCache.get(tempInt+"");
			}, "Thread"+i).start();
		}
		
	}
}
