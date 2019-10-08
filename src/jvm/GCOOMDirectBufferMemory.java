package jvm;

import java.nio.ByteBuffer;

import util.Utils;

/*
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 故障现象：
 * java.lang.OutOfMemoryError: Direct buffer memory
 * 
 *导致原因：
 *	写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（channel）和缓冲区（Buffer）的I/O方式
 *	他可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作
 *	这样能在一些场景中显著提高性能，因为避免了在java堆和native中来回复制数据
 */
public class GCOOMDirectBufferMemory {
	
	public static void main(String[] args) {
		System.out.println("配置的maxDirectoryMomory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1024) + "MB");
		Utils.sleep(3);
		ByteBuffer allocateDirect = ByteBuffer.allocateDirect(6*1024*1024);
	}

}
