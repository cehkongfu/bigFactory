package volatile_case;

import java.util.concurrent.atomic.AtomicInteger;

class MyData2 {
	volatile int data = 0;

	public void setData() {
		data = 60;
	}
	public void add1() {//在不加synchronized之前，说明volatile不保证原子性
		data +=1;
	}
	
	
	//凭什么AtomicInteger可以保证原子性而且还不用synchronized关键字，原因是CAS
	AtomicInteger atomicInteger = new AtomicInteger();
	public void addMyAtomic() {
		atomicInteger.getAndIncrement();
	}
}

public class VolatileHasNoAtomaticDisolved {
	public static void main(String[] args) {
		MyData2 myData = new MyData2();
		for(int i=0; i<20; i++) {
			new Thread(()->{
				for(int j=0; j<500; j++) {
					myData.add1();
					myData.addMyAtomic();
				}
			}, "Thread"+i).start();
		}
		
		//保证上面的线程全部执行完毕
		while(Thread.activeCount()>2) {//main线程+一个GC线程
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName()+"线程中data:"+myData.data );
		System.out.println(Thread.currentThread().getName()+"线程中data:"+myData.atomicInteger);
	}
}
