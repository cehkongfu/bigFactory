package volatile_case;

/**
 * Created by wkb on 2018/8/29.
 *
 * ����volatile ��
 * ����1�����̲߳��ӣ�Thread.sleep(10);�߳�1��flag��Ϊtrue,���̶߳�ȡ���������߳�flag=false�����߳�һֱѭ����(�����������������ܺõĻ������ܳ��ֲ��ˣ����Ҽ�̨��������)
 * ����2�����̼߳ӣ�Thread.sleep(10);��ô���߳̿��Զ�ȡ���߳�1�ı��flagֵ��˵���߳�1��flagֵˢ�µ��˹����ڴ���
 * ��ʱ���߶�Ϊtrue
 *
 * ��volatile: ��ʱ���̼߳Ӳ��� Thread.sleep(10); �����̶߳��������flag ��Ϊtrue��˵��
 * volatile�ؼ������ñ�����Ϊ�߳�֮��ɼ�
 *
 *
 */
class ThreadDemo1 implements Runnable {

	// �������
	private boolean flag = false;

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		try { Thread.sleep(200); } catch (InterruptedException e) {}
		flag = true;
		System.out.println("�����߳�flag=" + getFlag());
	}

	public static void main(String[] args) throws Exception {
		ThreadDemo1 td = new ThreadDemo1();
		new Thread(td).start();

		System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"��flag="+td.flag);
		while (true) {
            //Thread.sleep(10); //����������еĵ��ԾͶ�ȡ���������ڴ��е����ݣ����߳�һֱѭ��
			if (td.getFlag()) {
				System.out.println("���߳�flag:" + td.getFlag());
				break;
			}
		}
		System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"��flag="+td.flag);
	}
}