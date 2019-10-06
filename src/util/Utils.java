package util;

import java.util.concurrent.TimeUnit;

public class Utils {
	
	public static void sleep(int second) {
		try {
			TimeUnit.SECONDS.sleep(second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
