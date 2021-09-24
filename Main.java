//Name: Gabriel Shoderu
//Student Number: u16186258

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Company[] companies = new Company[6];
		
		CarWash carwash = new CarWash();
		
		for(int i = 0; i < 6; i++)
			companies[i] = new Company(carwash);

        for(Company company : companies)
        	company.start();
        
	}

}
