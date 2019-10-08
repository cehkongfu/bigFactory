package jvm;

import util.Utils;

//�鿴Ĭ������������
//java -XX:+PrintCommandLineFlags -version/
public class HelloGC {
	public static void main(String[] args) {
		long totalMemory = Runtime.getRuntime().totalMemory();
		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + " (�ֽ�)�� " + (totalMemory / (double)1024 / 1024 ) +"MB");
		System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + " (�ֽ�)�� " + (maxMemory / (double)1024 / 1024 ) +"MB");
		Utils.sleep(Integer.MAX_VALUE);
	}
}

/**
-XX:InitialHeapSize=267051648
-XX:MaxHeapSize=4272826368
-XX:+PrintCommandLineFlags
-XX:+UseCompressedClassPointers
-XX:+UseCompressedOops
-XX:-UseLargePagesIndividualAllocation
-XX:+UseParallelGC 
**/

/**
-Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 */

/**
-XX:InitialHeapSize=134217728
-XX:MaxHeapSize=4294967296
-XX:MetaspaceSize=536870912
-XX:+PrintCommandLineFlags
-XX:+PrintGCDetails
-XX:ThreadStackSize=1024
-XX:+UseCompressedClassPointers
-XX:+UseCompressedOops
-XX:-UseLargePagesIndividualAllocation
-XX:+UseSerialGC 
**/

/**
-XX:+PrintGCDetails	-->>��������ϸ��
-XX:+NewRatio 
-XX:MaxTenuringThreshold
**/