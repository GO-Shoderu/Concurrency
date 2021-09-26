import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

//Name: Gabriel Shoderu
//Student Number: u16186258

public class CarWash
{
	private volatile Queue<Car> washCars = new LinkedList<>(), dryCars = new LinkedList<>();
	
	Lock lock1 = new Bakery(6);
	Lock lock2 = new Bakery(6);

	public CarWash(){
		washCars.add(new Car('s', "Panda"));
		washCars.add(new Car('m', "Cerato"));
		washCars.add(new Car('s', "Swift"));
		washCars.add(new Car('l', "Defender"));
		washCars.add(new Car('m', "A3"));
		washCars.add(new Car('l', "Ranger"));
		washCars.add(new Car('s', "GTI"));
	}
	
	public void WashCar(int id, int workTime) {
		
		lock1.lock();
		
		try {
			if(!washCars.isEmpty()) {
				
//				System.out.println(String.format("Thread-%d is ready to wash a car", id));
				
				Thread.sleep((long) workTime);
				
				washCars.peek().washTime = washCars.peek().washTime - workTime;
					
				if(washCars.peek().washTime <= 0) {
					
					System.out.println(String.format("Thread-%d finished washing %s", ThreadID.get(), washCars.peek().name));
					dryCars.add(washCars.remove());
					
				}else {

					System.out.println(String.format("Thread-%d washed %s for %d ms. Time remaining: %d", ThreadID.get(), washCars.peek().name, (int) workTime, washCars.peek().washTime));

				}
					
			}else {
				//System.out.println("No cars to wash in the queue at the moment");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock1.unlock();
		}

	}
	
	public void dryCar(int id, int workTime) {
		
		lock2.lock();
		
		try {
			if(!dryCars.isEmpty()) {
				
//				System.out.println(String.format("Thread-%d is ready to dry a car", id));
				
				Thread.sleep((long) workTime);
				
				dryCars.peek().dryTime = dryCars.peek().dryTime - workTime;
					
				if(dryCars.peek().dryTime <= 0) {
					
					System.out.println(String.format("Thread-%d finished drying %s", ThreadID.get(), dryCars.peek().name));
					dryCars.remove();
					
				}else {

					System.out.println(String.format("Thread-%d dried %s for %d ms. Time remaining: %d", ThreadID.get(), dryCars.peek().name, (int) workTime, dryCars.peek().dryTime));

				}

			}else {
				//System.out.println("No cars to dry in the queue at the moment");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock2.unlock();
		}
		
	}
	
	public Queue<Car> getWashQueue(){
		return washCars;
	}
	
	public Queue<Car> getDryQueue(){
		return dryCars;
	}


}
