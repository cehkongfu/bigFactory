package volatile_case;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

	static AtomicReference<Integer> af = new AtomicReference<>(100);
	static AtomicStampedReference<Integer> asf = new AtomicStampedReference<>(100,1);
	public static void main(String[] args) {
		System.out.println("====================ABA问题的产生========================");
		new Thread(()->{
			af.compareAndSet(100, 101);
			af.compareAndSet(101, 100);
		},"t1").start();
		new Thread(()->{
			try {TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e) {e.printStackTrace();}
			System.out.println(af.compareAndSet(100, 2019) +"\t" + af.get());
		},"t2").start();


		try {TimeUnit.SECONDS.sleep(2);}catch(InterruptedException e) {e.printStackTrace();}
		System.out.println("====================ABA问题的解决========================");
		new Thread(()->{
			int stamp = asf.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t第1次版本号："+stamp);
			try {TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e) {e.printStackTrace();}
			asf.compareAndSet(100, 101, asf.getStamp(), asf.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t第2次版本号："+asf.getStamp());
			asf.compareAndSet(101, 100, asf.getStamp(), asf.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t第3次版本号："+asf.getStamp());
		},"t3").start();

		new Thread(()->{
			int stamp = asf.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t第1次版本号："+stamp);
			try {TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e) {e.printStackTrace();}
			boolean result = asf.compareAndSet(100, 2019, stamp, stamp+1);
			System.out.println(Thread.currentThread().getName()+"\t修改是否成功："+result+"\t当前最新实际版本号:"+asf.getStamp());
			System.out.println(Thread.currentThread().getName()+"\t当前实际最新值:"+asf.getReference());
		},"t4").start();
		
	}
	
}
