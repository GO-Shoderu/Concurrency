//Name: Gabriel Shoderu
//Student Number: u16186258

public class Company extends Thread{

	CarWash carwash;
	
	//creating the time related variables
	int workMin = 100;
	int workMax = 500;
	
	int breakMin = 50;
	int breakMax = 100;
	
	Company(CarWash c) {
		// TODO Auto-generated constructor stub
		carwash = c;
	}

	public void run()
	{
		int id = ThreadID.get();
		
		while(!carwash.getDryQueue().isEmpty() || !carwash.getWashQueue().isEmpty()) {
			
			//making my washers even and my driers odd
			if(id % 2 == 0) {
				
				//generating the randomness
				int workTime = (int) (Math.random() * (workMax - workMin + 1) + workMin);
				
				if(!carwash.getWashQueue().isEmpty()) {
					
					System.out.println(String.format("Thread-%d is ready to wash a car", id));
					carwash.WashCar(id, workTime);
					
					int breakTime = (int)(Math.random() * (breakMax - breakMin + 1) + breakMin);
					
					try {
						Thread.sleep((long) breakTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(String.format("Thread-%d is taking a break.", ThreadID.get()));
				}
					
				
			}else {
				
				//generating the randomness
				int workTime = (int) (Math.random() * (workMax - workMin + 1) + workMin);
				
				if(!carwash.getDryQueue().isEmpty()) {
					
					System.out.println(String.format("Thread-%d is ready to dry a car", id));
					carwash.dryCar(id, workTime);
					
					int breakTime = (int)(Math.random() * (breakMax - breakMin + 1) + breakMin);
					
					try {
						Thread.sleep((long) breakTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(String.format("Thread-%d is taking a break.", ThreadID.get()));
				}
					
			}
										
		}
	
	}
}
