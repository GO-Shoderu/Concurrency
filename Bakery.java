import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//Name: Gabriel Shoderu
//Student Number: u16186258

public class Bakery implements Lock
{
	private AtomicBoolean[] flag;
    private AtomicInteger[] label;
    
	private int thread_n;

	public Bakery(int n) {
		
        this.thread_n = n;
        
        flag = new AtomicBoolean[n];
        label = new AtomicInteger[n];
        
        for (int i = 0; i < n; i++) {
        	
            flag[i] = new AtomicBoolean();
            label[i] = new AtomicInteger();
            
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
	
	//alternative getMax method
//	public int getMax() {
//		
//		int maxSoFar = -1;
//
//        for (int i = 0; i < thread_n; i++) {
//            if (label[i].get() > maxSoFar) {
//                maxSoFar = label[i].get();
//            }
//        }
//
//        return maxSoFar;
//		
//	}
	
	private int getMax(AtomicInteger[] elementArray) {
		
		int maxValue = Integer.MIN_VALUE;
		
        for (AtomicInteger element : elementArray) {
            if (element.get() > maxValue) {
                maxValue = element.get();
            }
        }
        return maxValue;
		
	}

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		
		int i = ThreadID.get();
        flag[i].set(true);
        label[i].set(getMax(label) + 1);
        
        for (int k = 0; k < thread_n; k++) {
            while ((k != i) && flag[k].get() && ((label[k].get() < label[i].get()) || ((label[k].get() == label[i].get()) && k < i))) {
                //spin wait
            }
        }		
		
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		flag[ThreadID.get()].set(false);
	}

}
