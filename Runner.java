
public class Runner extends Thread {
	Counter counter;
	int maxIterations = 10000;
	
	public Runner(Counter c) {
		// TODO Auto-generated constructor stub
		counter = c;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(String.format("%s is running...", Thread.currentThread().getName()));
		
		for(int i = 0; i < maxIterations; i++) {
			System.out.println(String.format("%s Counter: %d", Thread.currentThread().getName(), counter.getAndIncrement()));
		}
	}

}
