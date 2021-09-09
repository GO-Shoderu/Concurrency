
public class Counter {
	private int counter = 0;
	
	public int getAndIncrement() {
		setCounter(getCounter() + 1);
		return getCounter();
	}

	//this is the getCounter
	private int getCounter() {
		return counter;
	}

	private void setCounter(int counter) {
		this.counter = counter;
	}
}
