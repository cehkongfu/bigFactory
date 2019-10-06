package blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import util.Utils;

class MyResource {
	private volatile boolean FLAG = true; //Ĭ�Ͽ���
	private AtomicInteger atomicInteger  = new AtomicInteger();
	
	BlockingQueue<String> blockingQueue = null;
	
	public MyResource(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
		System.out.println( blockingQueue.getClass().getName() );
	}
	
	public void myProd() throws Exception{
		String data = null;
		boolean retValue ;
		while(FLAG) {
			data = atomicInteger.incrementAndGet()+"";
			retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
			if(retValue) {
				System.out.println(Thread.currentThread().getName()+"\t �������"+data+"�ɹ�");
			}else {
				System.out.println(Thread.currentThread().getName()+"\t �������"+data+"ʧ��");
			}
			Utils.sleep(1);
		}
		System.out.println(Thread.currentThread().getName()+"\t���ϰ��ͣ�ˣ���ʾFLAG=false,��������");
	}
	
	public void myConsume() throws Exception{
		String result = null;
		while(FLAG) {
			result = blockingQueue.poll(2L, TimeUnit.SECONDS);
			if(null==result||"".equalsIgnoreCase(result)) {
				FLAG = false;
				System.out.println(Thread.currentThread().getName()+"\t ���Ѷ���"+result+"ʧ��");
				System.out.println();
				System.out.println();
				return;
			}
			Utils.sleep(1);
			System.out.println(Thread.currentThread().getName()+"\t����"+result+"�ɹ�");
		}
	}
	
	public void stop() {
		this.FLAG = false;
	}
	
}
public class ProduceConsumer_BlockQueueDemo {
	
	public static void main(String[] args) {
		MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
		
		for(int i=0; i<3; i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"\t�����߳�����");
				try {
					myResource.myProd();
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "Produce"+i).start();


			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"\t�����߳�����");
				try {
					myResource.myConsume();
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "Consumer"+i).start();
		}
		
		Utils.sleep(5);
		System.out.println("Main�߳̽�ͣ");
		myResource.stop();
	}
}