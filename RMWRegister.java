
public class RMWRegister {
	
	private int value;
	
	RMWRegister(int v){
		value = v;
	}
	
	public synchronized int getAndMumble() {
		//f(v) != v;
		
		int prior = this.value;
		
		this.value = this.value + 1;
		
		return prior;
	}

}
