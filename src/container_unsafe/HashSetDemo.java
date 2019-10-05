package container_unsafe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
//HashSet的底层用的就是HashMap，之所以存储的是一个，那是因为HashSet底层只需要存储key，不用管value
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
