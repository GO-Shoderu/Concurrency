import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class PetersonLock implements Lock {

    private volatile boolean[] flag;
    private volatile int victim;
    public PetersonLock() {

        flag = new boolean[2];
        flag[0]=false;
        flag[1]=false;
    }

    public void lock() {

        int i = ThreadID.get();
        int j = 1 - i;
        flag[i] = true; 
        victim = i; 

        while (flag[j] && victim == i) {};

    }

    public void unlock() {

        int i = ThreadID.get();
        flag[i] = false;

    }

    // Any class implementing Lock must provide these methods
    public Condition newCondition() {
        throw new java.lang.UnsupportedOperationException();
    }

    public boolean tryLock(long time, TimeUnit unit)
            throws InterruptedException {
        throw new java.lang.UnsupportedOperationException();
    }

    public boolean tryLock() {
        throw new java.lang.UnsupportedOperationException();
    }

    public void lockInterruptibly() throws InterruptedException {
        throw new java.lang.UnsupportedOperationException();
    }
}
