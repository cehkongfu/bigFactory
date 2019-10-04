package volatile_case;

class MyData1 {
	volatile int data = 0;

	public void setData() {
		data = 60;
	}
	public void add1() {//�ڲ���synchronized֮ǰ��˵��volatile����֤ԭ����
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
		
		//��֤������߳�ȫ��ִ�����
		while(Thread.activeCount()>2) {//main�߳�+һ��GC�߳�
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName()+"�߳���data:"+myData.data );
	}
}
