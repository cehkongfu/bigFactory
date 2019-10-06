package container_unsafe;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * дʱ���ƣ�
 * 	CopyOnWrite������дʱ���Ƶ���������һ���������Ԫ�ص�ʱ�򣬲�ֱ������ǰ����Object[]��ӣ������Ƚ���ǰ����Object[]
 * 	����Copy�����Ƴ�һ���µ�����Object[] newElements, Ȼ���µ�����object[] newElements�����Ԫ�أ������
 * Ԫ��֮���ٽ�ԭ����������ָ���µ����� setArray(newElements);�������ĺô��ǿ��Զ�CopyOnWrite�������в����Ķ���
 * ������Ҫ��������Ϊ��ǰ������������κ�Ԫ�ء�����CopyOnWrite����Ҳ��һ�ֶ�д�����˼�룬����д��ͬ������
 *
 *    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
 */

public class ArrayListDemo {
	public static void main(String[] args) {
		//List<String> list = new ArrayList<>();
		//List<String> list = Collections.synchronizedList(new ArrayList<>());
		List<String> list = new CopyOnWriteArrayList<>();
		for(int i=1; i<=10; i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			}, "Thread"+i).start();
		}
	}
}