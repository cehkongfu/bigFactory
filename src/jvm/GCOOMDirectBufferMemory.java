package jvm;

import java.nio.ByteBuffer;

import util.Utils;

/*
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * ��������
 * java.lang.OutOfMemoryError: Direct buffer memory
 * 
 *����ԭ��
 *	дNIO���򾭳�ʹ��ByteBuffer����ȡ����д�����ݣ�����һ�ֻ���ͨ����channel���ͻ�������Buffer����I/O��ʽ
 *	������ʹ��Native������ֱ�ӷ�������ڴ棬Ȼ��ͨ��һ���洢��Java�������DirectByteBuffer������Ϊ����ڴ�����ý��в���
 *	��������һЩ����������������ܣ���Ϊ��������java�Ѻ�native�����ظ�������
 *
 *ByteBuffer.allocate(capability) ��һ�ַ�ʽ����JVM���ڴ棬������GC��Ͻ��Ϊ�����ڲ���Ҫ�ڴ濽�������ٶ���ԽϿ�
 *
 *ByteBuffer.allocateDirect(capability)��2�ַ�ʽ�Ƿ���OS�����ڴ棬������GC��Ͻ��Χ�����ڲ���Ҫ�ڴ濽�������ٶ���ԽϿ�
 *
 *������������䱾���ڴ棬���ڴ����ʹ�ã���ôJVM�Ͳ���Ҫִ��GC��DirectByteBuffer�����ǾͲ��ᱻ����
 *
 *��ʱ����ڴ���㣬�������ڴ�����Ѿ�ʹ�ù��ˣ��ٴγ��Է��䱾���ڴ�ͻ����OutOfMemoryError���ǳ����ֱ�ӱ�����
 */
public class GCOOMDirectBufferMemory {
	
	public static void main(String[] args) {
		System.out.println("���õ�maxDirectoryMomory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1024) + "MB");
		Utils.sleep(3);
		ByteBuffer allocateDirect = ByteBuffer.allocateDirect(6*1024*1024);
	}

}
