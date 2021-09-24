import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

//Name: Gabriel Shoderu
//Student Number: u16186258

public class CarWash
{
	private volatile Queue<Car> washCars = new LinkedList<>(), dryCars = new LinkedList<>();
	
	Lock lock = new Bakery(6);

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
		
		lock.lock();
		
		try {
			if(!washCars.isEmpty()) {
				
				System.out.println(String.format("Thread-%d is ready to wash a car", id));
				
				Car carToBeWashed = washCars.remove();
				
				Thread.sleep((long) workTime);
				
				carToBeWashed.washTime = (long) (carToBeWashed.washTime - workTime);
					
				if(carToBeWashed.washTime <= 0) {
					
					System.out.println(String.format("Thread-%d finished washing %s", ThreadID.get(), carToBeWashed.name));
					dryCars.add(carToBeWashed);
					
				}else {

					System.out.println(String.format("Thread-%d washed %s for %d ms. Time remaining: %d", ThreadID.get(), carToBeWashed.name, (int) workTime, carToBeWashed.washTime));
					washCars.add(carToBeWashed);

				}
					
			}else {
				//System.out.println("No cars to wash in the queue at the moment");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}

	}
	
	public void dryCar(int id, int workTime) {
		
		lock.lock();
		
		try {
			if(!dryCars.isEmpty()) {
				
				System.out.println(String.format("Thread-%d is ready to dry a car", id));
				
				Car carToBeDried = dryCars.remove();
				
				Thread.sleep((long) workTime);
				
				carToBeDried.dryTime = (long) (carToBeDried.dryTime - workTime);
					
				if(carToBeDried.dryTime <= 0) {
					
					System.out.println(String.format("Thread-%d finished drying %s", ThreadID.get(), carToBeDried.name));
					
				}else {

					System.out.println(String.format("Thread-%d dried %s for %d ms. Time remaining: %d", ThreadID.get(), carToBeDried.name, (int) workTime, carToBeDried.dryTime));
					dryCars.add(carToBeDried);

				}

			}else {
				//System.out.println("No cars to dry in the queue at the moment");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
	
	public Queue<Car> getWashQueue(){
		return washCars;
	}
	
	public Queue<Car> getDryQueue(){
		return dryCars;
	}


}
