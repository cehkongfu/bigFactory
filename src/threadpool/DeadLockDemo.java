package threadpool;

class HoldLockThread implements Runnable {
	private String lockA;
	private String lockB;
	
	public HoldLockThread(String lockA, String lockB) {
		this.lockA = lockA;
		this.lockB = lockB;
	}
	@Override
	public void run() {
		synchronized(lockA) {
			System.out.println(Thread.currentThread().getName()+"\tӵ����"+lockA+"\t���Ի�ȡ��"+lockB);
			synchronized(lockB) {
				System.out.println("�������");
			}
		}
		
	}
}
public class DeadLockDemo {
	public static void main(String[] args) {
		String lockA = "lockA";
		String lockB = "lockB";
		HoldLockThread h1 = new HoldLockThread(lockA, lockB);
		HoldLockThread h2 = new HoldLockThread(lockB, lockA);
		new Thread(h1).start();
		new Thread(h2).start();
	}
}

//������
//��jps �ҵ��������ܵ�Ӧ�ó���Ľ��̺�
//jstack �����ҵ��Ľ��̺�