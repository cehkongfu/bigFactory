package volatile_case;

import java.util.concurrent.TimeUnit;

class MyData {
	volatile int data = 0;

	public void setData() {
		data = 60;
	}
	public void add1() {
		data +=1;
	}
}
public class VolatileVisible {
	public static void main(String[] args) {
		MyData myData = new MyData();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"线程:come in");
			//try {TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e) {e.printStackTrace();}
			myData.setData();
			System.out.println(Thread.currentThread().getName()+"线程:out");
		}, "AAA").start();
		
		while(myData.data==0) {
			System.out.println(Thread.currentThread().getName()+"线程:running");
		}
		System.out.println(myData.data);
	}
	

}
