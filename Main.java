public class Main {
    public static void main(String[] args) {
    	
    	System.out.println("Read-Modify-Write Consensus - Task_1");
    	System.out.println("The winning Register value is 0");
    	System.out.println();

        int numThreads = 2; 

        RMWConsensus consensusImpl = new RMWConsensus(numThreads) ;
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