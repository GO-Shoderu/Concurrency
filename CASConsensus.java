import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("rawtypes")
public class CASConsensus extends ConsensusProtocol{
	
	private final int FIRST = -1;
	private AtomicInteger r = new AtomicInteger(FIRST);
	
	static int counter;
	int tC;
	
	public CASConsensus(int threadCount) {
		super(threadCount);
		// TODO Auto-generated constructor stub
		tC = threadCount;
	}

	@Override
	public void decide() {
		// TODO Auto-generated method stub
		counter++;
		int i = ThreadID.get();
		System.out.println("Register value is " + r.get());	
		
		if(r.compareAndSet(FIRST, i)) {
			System.out.println("Thread " + ThreadID.get() + " got the register first");
			System.out.println("Thread " + ThreadID.get() + " decided on " + proposed[i]);
		}else {
			System.out.println("Thread " + ThreadID.get() + " decided on " + proposed[r.get()]);
		}
		
		if(counter == tC) {
			
			int iter = 0;
			
			while(iter < counter) {
				proposed[iter] = null;
				iter++;
			}
			
			counter = 0;
			r = new AtomicInteger(FIRST);
			
			System.out.println("################################################");
		}
		
		
	}
	
}
