package jvm;

import java.lang.ref.SoftReference;

/**
 * 
 * @author robot
 *	jvm配置，故意产生大对象并配置小的内存，让它内存不够用了导致OOM,看软引用的回收情况
 *	-Xms5m -Xmx5m -XX:+PrintGCDetails
 */
public class SoftReferenceDemo {
	
	private static void softRef_Memory_NotEnough() {
		Object o1 = new Object();
		SoftReference<Object> softReference = new SoftReference<>(o1);
		System.out.println(o1);
		System.out.println(softReference.get());

		o1 = null;
		try {
			byte[] bytes = new byte[30*1024*1024];
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(o1);
			System.out.println(softReference.get());
			
		}
	}

	public static void main(String[] args) {
		softRef_Memory_NotEnough();
	}
}

/**

[GC (Allocation Failure) [PSYoungGen: 1023K->504K(1536K)] 1023K->684K(5632K), 0.0010439 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
java.lang.Object@7ea987ac
java.lang.Object@7ea987ac
[GC (Allocation Failure) [PSYoungGen: 590K->496K(1536K)] 770K->833K(5632K), 0.0007561 secs] [Times: user=0.02 sys=0.08, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 496K->488K(1536K)] 833K->841K(5632K), 0.0004423 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[Full GC (Allocation Failure) [PSYoungGen: 488K->0K(1536K)] [ParOldGen: 353K->670K(4096K)] 841K->670K(5632K), [Metaspace: 2751K->2751K(10567
68K)], 0.0031932 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] 670K->670K(5632K), 0.0003135 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[Full GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] [ParOldGen: 670K->657K(4096K)] 670K->657K(5632K), [Metaspace: 2751K->2751K(1056768
K)], 0.0037410 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
null
null
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at jvm.SoftReferenceDemo.softRef_Memory_NotEnough(SoftReferenceDemo.java:21)
	at jvm.SoftReferenceDemo.main(SoftReferenceDemo.java:32)
Heap
 PSYoungGen      total 1536K, used 41K [0x00000000ffe00000, 0x0000000100000000, 0x0000000100000000)
  eden space 1024K, 4% used [0x00000000ffe00000,0x00000000ffe0a458,0x00000000fff00000)
  from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
  to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
 ParOldGen       total 4096K, used 657K [0x00000000ffa00000, 0x00000000ffe00000, 0x00000000ffe00000)
  object space 4096K, 16% used [0x00000000ffa00000,0x00000000ffaa47f8,0x00000000ffe00000)
 Metaspace       used 2781K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 309K, capacity 386K, committed 512K, reserved 1048576K
**/