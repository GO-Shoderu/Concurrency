import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//Name: Gabriel Shoderu
//Student Number: u16186258

public class Filter implements Lock
{
	private AtomicInteger[] level;
    private AtomicInteger[] victim;
    
    private int thread_n;
    
    public Filter(int n) {
    	
        this.thread_n = n;
        
        level = new AtomicInteger[n];
        victim = new AtomicInteger[n];
        
        for (int i = 0; i < n; i++) {
        	
            level[i] = new AtomicInteger();
            victim[i] = new AtomicInteger();
            
        }
    }

	public void lockInterruptibly() throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock()
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public Condition newCondition()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		
		int me = ThreadID.get();
		
        for (int i = 1; i < thread_n; i++) {
        	
            level[me].set(i);
            victim[i].set(me);
            
            for (int k = 0; k < thread_n; k++) {
                while ((k != me) && (level[k].get() >= i && victim[i].get() == me)) {
                    //spin wait
                }
            }
        }
		
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
		int me = ThreadID.get();
        level[me].set(0);
		
	}

}
