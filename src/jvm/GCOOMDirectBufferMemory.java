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
 */
public class GCOOMDirectBufferMemory {
	
	public static void main(String[] args) {
		System.out.println("���õ�maxDirectoryMomory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1024) + "MB");
		Utils.sleep(3);
		ByteBuffer allocateDirect = ByteBuffer.allocateDirect(6*1024*1024);
	}

}
