package someLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 	����߳�ͬʱ��һ����Դ��һ������û�У�����Ϊ����߲�����������߳�ͬʱ��ȡ������Դû������
 	����д�����Ͳ�һ���ˣ����������߳�ͬʱ����д����
 	
 	��-�� ���Թ���
 	��-д ���ܹ���
 	д-д ���Թ���
 */
class MyCache{
	private volatile Map<String, Object> map = new HashMap<>();
	private ReentrantReadWriteLock wrlock = new ReentrantReadWriteLock();

	public void put(String key, Object value) {
		wrlock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t ����д�룺" + key);
			try {TimeUnit.MILLISECONDS.sleep(300);} catch(InterruptedException e) {e.printStackTrace();}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"\t д�����:");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			wrlock.writeLock().unlock();
		}
	}
	public void get(String key) {
		wrlock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t ���ڶ�����" + key);
			try {TimeUnit.MILLISECONDS.sleep(300);} catch(InterruptedException e) {e.printStackTrace();}
			map.get(key);
			System.out.println(Thread.currentThread().getName()+"\t �������:");
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
