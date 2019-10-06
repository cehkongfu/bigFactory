package blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
��Ŀ��һ����ʼֵΪ��ı����������̶߳��佻�������һ����1��һ����1����5��

1	�߳�	����	��Դ��
2	�ж�	�ɻ�	

**/
//�漰�����̣߳�һ������һ����Դ��
class ShareData {
	private int number = 0;
	private Lock lock =new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void increment() throws Exception {
		lock.lock();
		try {
		//1�ж�
		while(number!=0) {
			//�ȴ�����������
			condition.await();
		}

		//2�ɻ�
		number++;
		System.out.println(Thread.currentThread().getName() +"\t"+number);

		//3֪ͨ����
		condition.signalAll();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws Exception {
		lock.lock();
		try {
		//1�ж�
		while(number==0) {
			//�ȴ�����������
			condition.await();
		}

		//2�ɻ�
		number--;
		System.out.println(Thread.currentThread().getName() +"\t"+number);

		//3֪ͨ����
		condition.signalAll();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
public class ProduceConsumer_TraditionalDemo {
	public static void main(String[] args) {

		ShareData shareData = new ShareData();
		for(int k=1; k<4; k++) {
			new Thread(()->{
				for(int i=0; i<5; i++) {
					try {
						shareData.increment();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			},"Producer"+k).start();
		}
		
		for(int k=1; k<4; k++) {
			new Thread(()->{
				for(int i=0; i<5; i++) {
					try {
						shareData.decrement();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			},"Consumer"+k).start();
		}
		
	}
}
