package volatile_case;

class MyData1 {
	volatile int data = 0;

	public void setData() {
		data = 60;
	}
	public void add1() {//在不加synchronized之前，说明volatile不保证原子性
		data +=1;
	}
}

public class VolatileHasNoAtomatic {
	public static void main(String[] args) {
		MyData1 myData = new MyData1();
		for(int i=0; i<20; i++) {
			new Thread(()->{
				for(int j=0; j<500; j++) {
					myData.add1();
				}
			}, "Thread"+i).start();
		}
		
		//保证上面的线程全部执行完毕
		while(Thread.activeCount()>2) {//main线程+一个GC线程
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName()+"线程中data:"+myData.data );
	}
}
