package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SelfDefineThreadPool {

	public static void main(String[] args) {
		// new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
		// workQueue, threadFactory, handler)
		//最大线程数为多大合适？
		//需要看任务是CPU密集型还是IO密集型，如果是CPU密集型，那就是CPU核心数+1，如果是IO秘籍行，那就是CPU核心数*2
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 
				5, 
				1L, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(3),
				Executors.defaultThreadFactory(), 
				new ThreadPoolExecutor.DiscardPolicy());
		
		//模拟10个用户来办理业务，每个用户都是一个来自外部的请求线程
		try {
			for(int i=1; i<=10; i++) {
				threadPool.execute(()->{
					System.out.println(Thread.currentThread().getName()+"\t\t办理业务");
				});
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			threadPool.shutdown();
		}
	}

}
