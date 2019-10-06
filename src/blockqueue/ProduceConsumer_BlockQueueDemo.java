package blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import util.Utils;

class MyResource {
	private volatile boolean FLAG = true; //默认开启
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
				System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
			}else {
				System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
			}
			Utils.sleep(1);
		}
		System.out.println(Thread.currentThread().getName()+"\t大老板叫停了，表示FLAG=false,生产结束");
	}
	
	public void myConsume() throws Exception{
		String result = null;
		while(FLAG) {
			result = blockingQueue.poll(2L, TimeUnit.SECONDS);
			if(null==result||"".equalsIgnoreCase(result)) {
				FLAG = false;
				System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"失败");
				System.out.println();
				System.out.println();
				return;
			}
			Utils.sleep(1);
			System.out.println(Thread.currentThread().getName()+"\t消费"+result+"成功");
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
				System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
				try {
					myResource.myProd();
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "Produce"+i).start();


			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
				try {
					myResource.myConsume();
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "Consumer"+i).start();
		}
		
		Utils.sleep(5);
		System.out.println("Main线程叫停");
		myResource.stop();
	}
}