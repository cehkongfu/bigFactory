package jvm;

import java.util.Random;

public class JavaHeapSpaceDemo {
	
	public static void main(String[] args) {
		String str = "atguigu";
		while(true) {
			str += new Random().nextInt(11111111) + new Random().nextInt(222222222);//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
			str.intern();
		}
	}

}
