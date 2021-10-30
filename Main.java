public class Main {
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
    	
    	System.out.println("Compare-And-Swap Consensus - Task_2");
    	System.out.println("The winning Register value is -1");
    	System.out.println();

        int numThreads = 5; 

        CASConsensus consensusImpl = new CASConsensus(numThreads) ;
        ConsensusThread[] friends = new ConsensusThread[numThreads] ;
        
        for (int i = 0 ; i < numThreads ; i++){
            friends[i] = new ConsensusThread(consensusImpl) ;
        }
        
        for (int i = 0 ; i < numThreads ; i++){
            friends[i].start();
        }

        for (int i = 0 ; i < numThreads ; i++){
            try {
                friends[i].join();
            } catch (Exception e) {
                //TODO: handle exception
            }

        }
        
    }
}