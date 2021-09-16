// Name: Gabriel Shoderu
// Student Number: u16186258

public class Queue extends Thread
{
	Store store;
	int maxIterations = 4;

	public Queue(Store s){
		store = s;
	}

	@Override
	public void run()
	{
		//System.out.println(String.format("%s is running...", Thread.currentThread().getName()));
		int id = ThreadID.get();
		for(int i = 0; i < maxIterations; i++) {
			System.out.println(String.format("Thread-%d Person %d is trying to enter the store", id, i));
			store.enterStore(id, i);
		}
	
	}
}
