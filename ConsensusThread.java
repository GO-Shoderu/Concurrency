public class ConsensusThread extends Thread
{
	private Consensus<Integer> consensus;
	

	ConsensusThread(Consensus<Integer> consensusObject)
	{
		consensus = consensusObject;
	}

	public void run()
	{
		int i = 0;
		
		int minPropose = 100;
		int maxPropose = 200;
		
		int minWait = 50;
		int maxWait = 100;
		
		while(i < 5) {
			//creating proposal	
			int amountToSpend = (int) (Math.random() * (maxPropose - minPropose + 1) + minPropose);
			
			consensus.propose(amountToSpend);
			System.out.println("Thread " + ThreadID.get() + " proposed " + amountToSpend);
			
			//creating the sleep time
			int waitingTime = (int) (Math.random() * (maxWait - minWait + 1) + minWait);
			
			try {
				Thread.sleep((long) waitingTime);
				consensus.decide();	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			i++;
		}
	}
}
