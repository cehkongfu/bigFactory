package volatile_case;

class Data{
	int a,b,x,y=0;
	public void method1() {
		x = a;
		b = 1;
	}
	public void method2() {
		y = b;
		a = 2;
	}
}

public class IndexReRank {
	public static void main(String[] args) {
		Data data = new Data();
		for(int i=0; i<100000; i++) {
			new Thread(()->{
					data.method1();
			}).start();
			new Thread(()->{
					data.method2();
			}).start();
			System.out.println("x="+data.x +" y="+data.y);
		}
	}

}
