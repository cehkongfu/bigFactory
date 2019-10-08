package jvm;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 
 * @author robot
 * 弱引用什么时候都会被回收
 */
public class WeakHashMapDemo {
	
	private static void myHashMap() {
		HashMap<Object, Object> map = new HashMap<>();
		Integer key = new Integer(1);
		String value = "HashMap";
		map.put(key, value);
		System.out.println(map);
		key = null;
		System.out.println(map);
		System.gc();
		System.out.println(map + "\t" + map.size());

	}

	public static void main(String[] args) {
		myHashMap();
		System.out.println("=====================");
		myWeakHashMap();
	}

	private static void myWeakHashMap() {
		WeakHashMap<Object, Object> map = new WeakHashMap<>();
		Integer key = new Integer(2);
		String value = "WeakHashMap";
		map.put(key, value);
		System.out.println(map);
		key = null;
		System.out.println(map);
		System.gc();
		System.out.println(map + "\t" + map.size());		
	}
}

