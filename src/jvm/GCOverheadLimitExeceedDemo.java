package jvm;

import java.util.ArrayList;
/**
 * @author robot
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 
 * GC回收时间过长时会抛出OutOfMemoryError。过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存
 * 连续回收多次GC都只回收了不到2%的极端情况下才会抛出。加入不抛出GC overhead limit错误会发生什么情况呢？
 * 那就是GC清理的这么点内存很快会再次被填满，迫使GC再次执行。这就形成的了恶性循环，
 * CPU使用率一直是100%，而GC却没有任何成果。
 */

public class GCOverheadLimitExeceedDemo {
	
	public static void main(String[] args) {
		int i=0;
		ArrayList<String> list = new ArrayList<>();
		try {
			while(true) {
				list.add(String.valueOf(++i).intern());
			}
		} catch (Throwable e) {
			System.out.println("####################:i="+i);
			e.printStackTrace();
			throw e;
		}
	}

}

/**
java.lang.OutOfMemoryError: GC overhead limit exceeded
at java.lang.Integer.toString(Unknown Source)
at java.lang.String.valueOf(Unknown Source)
at jvm.GCOverheadLimitExeceedDemo.main(GCOverheadLimitExeceedDemo.java:16)
Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
at java.lang.Integer.toString(Unknown Source)
at java.lang.String.valueOf(Unknown Source)
at jvm.GCOverheadLimitExeceedDemo.main(GCOverheadLimitExeceedDemo.java:16)
**/