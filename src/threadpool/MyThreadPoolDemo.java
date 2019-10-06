package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.Utils;

/**
 * @author robot
 * ��4�ֻ��/ʹ��java���̵߳ķ�ʽ���̳߳�
 *
 */
public class MyThreadPoolDemo {

	public static void main(String[] args) {
		//ExecutorService threadPool = Executors.newFixedThreadPool(5);//һ��5�������߳�
		//ExecutorService threadPool = Executors.newSingleThreadExecutor();//һ��5�������߳�
		ExecutorService threadPool = Executors.newCachedThreadPool();//һ��N�������߳�,����ԱȽϴ�
		//���һ���߳��ܸ㶨��������һ���̣߳������Ҫ����̲߳���������񣬾ͻ��ö���߳�
		
		//ģ��10���û�������ҵ��ÿ���û�����һ�������ⲿ�������߳�
		try {
			for(int i=1; i<=10; i++) {
				threadPool.execute(()->{
					System.out.println(Thread.currentThread().getName()+"\t\t����ҵ��");
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
