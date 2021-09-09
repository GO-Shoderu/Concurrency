public class Main {

    public static void main(String[] args){
    	Counter c = new Counter();
    	Thread t1 = new Runner(c);
    	Thread t2 = new Runner(c);
    	
    	t1.start();
    	t2.start();
    	
    	try {
			t1.join();
	    	t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println("Both threads are done running.... Practical_1_Task_1...");
    }
}
