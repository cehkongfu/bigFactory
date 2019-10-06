package someLock;

import java.sql.Time;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import util.Utils;

public class SemaphoreDemo {

	public static void main(String[] args) {
		//ģ��������λ
		Semaphore semaphore = new Semaphore(3);
		
		for(int i=1; i<=6; i++) {
			new Thread(()->{
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"\t������λ");
					Utils.sleep(3);
					System.out.println(Thread.currentThread().getName()+"\tͣ��3���뿪��λ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			}, "Thread"+i).start();
		}
	}
}
