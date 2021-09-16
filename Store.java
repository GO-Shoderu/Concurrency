import java.util.concurrent.locks.Lock;

//Name: Gabriel Shoderu
//Student Number: u16186258

public class Store
{
	//Filter n = new Filter(2);
	Lock lock = new Bakery(4);
	
	//creating the time related variables
	int min = 200;
	int max = 1000;
	
	double time = Math.random() * (max - min + 1) + min;

	public void enterStore(int id, int i){
		
		lock.lock();
		System.out.println(String.format("Thread-%d Person %d has entered the store", id, i));
		
		try {
			Thread.sleep((long) time);
			System.out.println(String.format("Thread-%d Person %d has exited the store", id, i));			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
			
		
	}
}
