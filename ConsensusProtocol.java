public abstract class ConsensusProtocol<T> implements Consensus<T>
{
	public volatile Object[] proposed;

	public ConsensusProtocol(int threadCount)	{
		proposed = new Object[threadCount];
	}

	public void propose(T value){
		
		if(proposed[ThreadID.get()] != null) {
			synchronized(proposed[ThreadID.get()]) {
				while(proposed[ThreadID.get()] != null)
				{}	
			}
		}

		proposed[ThreadID.get()] = value;
	}

	abstract public void decide();
}
