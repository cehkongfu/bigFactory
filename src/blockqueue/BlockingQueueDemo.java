package blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
	public static void main(String[] args) {
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(3);
		System.out.println( bq.add("a") );
		System.out.println( bq.add("b") );
		System.out.println( bq.add("c") );
		
		System.out.println(bq.element());
		
		System.out.println(bq.remove());
		System.out.println(bq.remove());
		System.out.println(bq.remove());
	}
}
