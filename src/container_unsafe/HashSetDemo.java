package container_unsafe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
//HashSet�ĵײ��õľ���HashMap��֮���Դ洢����һ����������ΪHashSet�ײ�ֻ��Ҫ�洢key�����ù�value
public class HashSetDemo {
	public static void main(String[] args) {
		Set<String> set= new HashSet<>();
		for(int i=1; i<=30; i++) {
			new Thread(()->{
				set.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(set);
			}, "Thread"+i).start();
		}
	}

}
