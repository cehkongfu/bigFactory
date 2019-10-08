package jvm;

import java.util.ArrayList;
/**
 * @author robot
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 
 * GC����ʱ�����ʱ���׳�OutOfMemoryError�������Ķ����ǣ�����98%��ʱ��������GC���һ����˲���2%�Ķ��ڴ�
 * �������ն��GC��ֻ�����˲���2%�ļ�������²Ż��׳������벻�׳�GC overhead limit����ᷢ��ʲô����أ�
 * �Ǿ���GC�������ô���ڴ�ܿ���ٴα���������ʹGC�ٴ�ִ�С�����γɵ��˶���ѭ����
 * CPUʹ����һֱ��100%����GCȴû���κγɹ���
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