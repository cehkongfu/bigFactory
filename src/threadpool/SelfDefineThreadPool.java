package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SelfDefineThreadPool {

	public static void main(String[] args) {
		// new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
		// workQueue, threadFactory, handler)
		//����߳���Ϊ�����ʣ�
		//��Ҫ��������CPU�ܼ��ͻ���IO�ܼ��ͣ������CPU�ܼ��ͣ��Ǿ���CPU������+1�������IO�ؼ��У��Ǿ���CPU������*2
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 
				5, 
				1L, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(3),
				Executors.defaultThreadFactory(), 
				new ThreadPoolExecutor.DiscardPolicy());
		
		//ģ��10���û�������ҵ��ÿ���û�����һ�������ⲿ�������߳�
		try {
			for(int i=1; i<=10; i++) {
				threadPool.execute(()->{
					System.out.println(Thread.currentThread().getName()+"\t\t����ҵ��");
				});
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			threadPool.shutdown();
		}
	}

}
