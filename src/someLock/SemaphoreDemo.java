package someLock;

import java.sql.Time;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import util.Utils;

public class SemaphoreDemo {

	public static void main(String[] args) {
		//模拟三个车位
		Semaphore semaphore = new Semaphore(3);
		
		for(int i=1; i<=6; i++) {
			new Thread(()->{
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"\t抢到车位");
					Utils.sleep(3);
					System.out.println(Thread.currentThread().getName()+"\t停留3秒离开车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			}, "Thread"+i).start();
		}
	}
}
