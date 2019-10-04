package volatile_case;

/**
 * Created by wkb on 2018/8/29.
 *
 * 不加volatile ：
 * 现象1：主线程不加：Thread.sleep(10);线程1将flag改为true,主线程读取不到，主线程flag=false，主线程一直循环，(这种情况如果电脑性能好的话，可能出现不了，多找几台电脑试试)
 * 现象2：主线程加：Thread.sleep(10);那么主线程可以读取到线程1改变的flag值，说明线程1将flag值刷新到了公共内存中
 * 此时两者都为true
 *
 * 加volatile: 此时主线程加不加 Thread.sleep(10); 两个线程都会结束：flag 都为true，说明
 * volatile关键字是让变量变为线程之间可见
 *
 *
 */
class ThreadDemo1 implements Runnable {

	// 共享变量
	private boolean flag = false;

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		try { Thread.sleep(200); } catch (InterruptedException e) {}
		flag = true;
		System.out.println("其他线程flag=" + getFlag());
	}

	public static void main(String[] args) throws Exception {
		ThreadDemo1 td = new ThreadDemo1();
		new Thread(td).start();

		System.out.println("当前线程："+Thread.currentThread().getName()+"中flag="+td.flag);
		while (true) {
            //Thread.sleep(10); //不加这个，有的电脑就读取不到公共内存中的数据，主线程一直循环
			if (td.getFlag()) {
				System.out.println("主线程flag:" + td.getFlag());
				break;
			}
		}
		System.out.println("当前线程："+Thread.currentThread().getName()+"中flag="+td.flag);
	}
}