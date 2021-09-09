public class Counter extends PetersonLock{
	private int counter = 0;
	PetersonLock lock = new PetersonLock();

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
