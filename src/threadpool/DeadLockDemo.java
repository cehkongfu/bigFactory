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
			System.out.println(Thread.currentThread().getName()+"\t拥有锁"+lockA+"\t尝试获取锁"+lockB);
			synchronized(lockB) {
				System.out.println("完成任务");
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

//找死锁
//先jps 找到现在在跑的应用程序的进程号
//jstack 上面找到的进程号