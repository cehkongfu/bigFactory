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
//Runnable�ӿ�û�з���ֵ��Callable�з���ֵ
//Runnable�������쳣��Callable�����쳣
//������һ��

//Ϊʲô�Ѿ���Runnable�ӿڣ�����Ҫ��Callable�ӿ�
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
		//һ�㷵�ؽ���ŵ����Ҫ����Callable�̵߳ļ���������Ϊget()�����������ģ�
		//���û�м�����ɾ�Ҫȥǿ�󣬻ᵼ��������ֵ���������
		System.out.println( "***************result:"+(result01+result02) );
	}

}
