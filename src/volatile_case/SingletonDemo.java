package volatile_case;

public class SingletonDemo {
	
	private static SingletonDemo sd = null; 
	private SingletonDemo() {
		System.out.println(Thread.currentThread().getName()+"线程生成单例对象");
	}
	
	public static SingletonDemo getInstance() {
		if(sd==null) {
			synchronized(SingletonDemo.class) {
				if(sd==null) {
					sd = new SingletonDemo();
				}
			}
		}
		return sd;
	}
	
	public static void main(String[] args) {
		//System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
		//System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
		//System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
		
		for(int i=1; i<=10; i++) {
			new Thread(()->{
				SingletonDemo.getInstance();
			}, "Thread"+i).start();
		}
	}

}
