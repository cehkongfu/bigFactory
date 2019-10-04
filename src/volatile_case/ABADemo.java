package volatile_case;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

	static AtomicReference<Integer> af = new AtomicReference<>(100);
	static AtomicStampedReference<Integer> asf = new AtomicStampedReference<>(100,1);
	public static void main(String[] args) {
		System.out.println("====================ABA����Ĳ���========================");
		new Thread(()->{
			af.compareAndSet(100, 101);
			af.compareAndSet(101, 100);
		},"t1").start();
		new Thread(()->{
			try {TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e) {e.printStackTrace();}
			System.out.println(af.compareAndSet(100, 2019) +"\t" + af.get());
		},"t2").start();


		try {TimeUnit.SECONDS.sleep(2);}catch(InterruptedException e) {e.printStackTrace();}
		System.out.println("====================ABA����Ľ��========================");
		new Thread(()->{
			int stamp = asf.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t��1�ΰ汾�ţ�"+stamp);
			try {TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e) {e.printStackTrace();}
			asf.compareAndSet(100, 101, asf.getStamp(), asf.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t��2�ΰ汾�ţ�"+asf.getStamp());
			asf.compareAndSet(101, 100, asf.getStamp(), asf.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t��3�ΰ汾�ţ�"+asf.getStamp());
		},"t3").start();

		new Thread(()->{
			int stamp = asf.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t��1�ΰ汾�ţ�"+stamp);
			try {TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e) {e.printStackTrace();}
			boolean result = asf.compareAndSet(100, 2019, stamp, stamp+1);
			System.out.println(Thread.currentThread().getName()+"\t�޸��Ƿ�ɹ���"+result+"\t��ǰ����ʵ�ʰ汾��:"+asf.getStamp());
			System.out.println(Thread.currentThread().getName()+"\t��ǰʵ������ֵ:"+asf.getReference());
		},"t4").start();
		
	}
	
}
