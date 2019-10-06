package threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
//Runnable接口没有返回值，Callable有返回值
//Runnable不会抛异常，Callable会抛异常
//方法不一样

//为什么已经有Runnable接口，还需要有Callable接口
//

class MyThread implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("***********come in Callable***************");
		return 1024;
	}
}
public class CallableDemo {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
		Thread t1 = new Thread(futureTask, "AA");
		t1.start();
		
		int result01 = 100;
		int result02 = futureTask.get();
		//一般返回结果放到最后，要求获得Callable线程的计算结果，因为get()方法是阻塞的，
		//如果没有计算完成就要去强求，会导致阻塞，值到计算完成
		System.out.println( "***************result:"+(result01+result02) );
	}

}
