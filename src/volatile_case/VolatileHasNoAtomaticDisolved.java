package volatile_case;

import java.util.concurrent.atomic.AtomicInteger;

class MyData2 {
	volatile int data = 0;

	public void setData() {
		data = 60;
	}
	public void add1() {//�ڲ���synchronized֮ǰ��˵��volatile����֤ԭ����
		data +=1;
	}
	
	
	//ƾʲôAtomicInteger���Ա�֤ԭ���Զ��һ�����synchronized�ؼ��֣�ԭ����CAS
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
		
		//��֤������߳�ȫ��ִ�����
		while(Thread.activeCount()>2) {//main�߳�+һ��GC�߳�
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName()+"�߳���data:"+myData.data );
		System.out.println(Thread.currentThread().getName()+"�߳���data:"+myData.atomicInteger);
	}
}
