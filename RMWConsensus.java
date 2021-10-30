import java.util.concurrent.ConcurrentLinkedQueue;

public class RMWConsensus extends ConsensusProtocol{

	int v = 0;
	private RMWRegister r = new RMWRegister(v);
	
	

	public RMWConsensus(int threadCount) {
		super(threadCount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decide() {
		// TODO Auto-generated method stub
		
		int i = ThreadID.get();
		int j = 1 - i;
		
		int mumbled = r.getAndMumble();
		
		System.out.println("Register value is " + mumbled);		
		
		if(mumbled == v) {
			System.out.println("Thread " + ThreadID.get() + " decided on " + proposed[i]);
		}
		else {
			System.out.println("Thread " + ThreadID.get() + " decided on " + proposed[j]);
			
			//resetting the values stored
			proposed[i] = null;
			proposed[j] = null;
			r = new RMWRegister(v);
			
			System.out.println("################################################");
		}
		
	}
	
	synchronized void reset() {
		
	}
	
}
