package jvm;

import java.lang.ref.WeakReference;

/**
 * 
 * @author robot
 * 弱引用什么时候都会被回收
 */
public class WeakReferenceDemo {
	
	private static void weakRef_Memory() {
		Object o1 = new Object();
		WeakReference<Object> WeakReference = new WeakReference<>(o1);
		System.out.println(o1);
		System.out.println(WeakReference.get());
		System.out.println("============================");
		o1 = null;
		System.gc();
		System.out.println(o1);
		System.out.println(WeakReference.get());
	}

	public static void main(String[] args) {
		weakRef_Memory();
	}
}

