package test;

public class test2Thread implements Runnable {

	public test2Thread(int f) {
		k=f;
	}

	private int k = 1;

	public int getK() {
		return k;
	}

	public synchronized void setK(int k) {
		this.k = k;
	}

	@Override
	public synchronized void run() {

		try {
			while (true) {
				System.out.println(k);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
