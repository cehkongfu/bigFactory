package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.Utils;

/**
 * @author robot
 * 第4种获得/使用java多线程的方式、线程池
 *
 */
public class MyThreadPoolDemo {

	public static void main(String[] args) {
		//ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
		//ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池5个处理线程
		ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个处理线程,灵活性比较大
		//如果一个线程能搞定，就是用一个线程，如果需要多个线程才能完成任务，就会用多个线程
		
		//模拟10个用户来办理业务，每个用户都是一个来自外部的请求线程
		try {
			for(int i=1; i<=10; i++) {
				threadPool.execute(()->{
					System.out.println(Thread.currentThread().getName()+"\t\t办理业务");
				});
				Utils.sleep(1);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			threadPool.shutdown();
		}
	}
}
