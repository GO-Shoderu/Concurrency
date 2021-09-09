//importing the locks needed
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
	private int counter = 0;
	private Lock lock = new ReentrantLock();

	public int getAndIncrement() {

		lock.lock();

		try {
			setCounter(getCounter() + 1);
			return getCounter();
		}finally {
			lock.unlock();
		}

	}

	private int getCounter() {
		return counter;
	}

	private void setCounter(int counter) {
		this.counter = counter;
	}
}
