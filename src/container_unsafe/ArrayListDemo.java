package container_unsafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 
 * @author robot
 * 1 故障现象：
 * 	java.util.ConcurrentModificationException
 * 2 导致原因
 * 		并发争抢修改导致，参考我们的花名册签名情况。
 * 		一个人正在写入，另外一个同学过来抢夺，导致数据不一致，发生异常
 * 
 * 3 解决方案
 * 	3.1 Vector (较low的一种方法)
 * 	3.2 Collections.synchronizedList(new ArrayList<>());
 *	3.3 JUC new CopyOnWriteArrayList<>();
 */
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写时复制：
 * 	CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]
 * 	进行Copy，复制出一个新的容器Object[] newElements, 然后新的容器object[] newElements里添加元素，添加完
 * 元素之后，再将原容器的引用指向新的容器 setArray(newElements);这样做的好处是可以对CopyOnWrite容器进行并发的读，
 * 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
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