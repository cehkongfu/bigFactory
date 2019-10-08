package jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import util.Utils;

public class PhantomReferenceDemo {
	
	public static void main(String[] args) {
		Object o1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);		
		
		System.out.println(o1);
		System.out.println(phantomReference.get());
		System.out.println(referenceQueue.poll());
		System.out.println("==========================");
		o1 = null;
		System.gc();
		Utils.sleep(2);
		System.out.println(o1);
		System.out.println(phantomReference.get());
		System.out.println(referenceQueue.poll());
	}

}
