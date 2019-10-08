package jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import util.Utils;

public class ReferenceQueueDemo {

	public static void main(String[] args) {
		Object o1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);
		
		System.out.println(o1);
		System.out.println(weakReference.get());
		System.out.println(referenceQueue.poll());
		
		System.out.println("===========================");
		o1 = null;
		System.gc();
		Utils.sleep(2);
		System.out.println(o1);
		System.out.println(weakReference.get());
		System.out.println(referenceQueue.poll());
	}
}
