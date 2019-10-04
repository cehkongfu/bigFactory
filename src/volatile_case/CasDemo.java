package volatile_case;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {
	
	public static void main(String[] args) {
		AtomicInteger ai = new AtomicInteger(5);
		System.out.println(ai.compareAndSet(5, 2019) +"\t current data: "+ai.get());
		System.out.println(ai.compareAndSet(2019, 1024) +"\t current data: "+ai.get());
	}

}
