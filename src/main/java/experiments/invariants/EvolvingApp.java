package experiments.invariants;

public class EvolvingApp {
	
	public int MAX = 128;
	private int count = 0; //assume max count is MAX
	
	private int another = 1; //show remain unchanged
	
	// cycleIncrement will increment the count by one
	// however, if the count reaches its maximum, it will be reset to 1
	public int cycleIncrement() {
		//original code
		//return (++this.count) % MAX;

		/**
		 * fixed code
		 */
		return this.count==MAX-1 ? 
				(this.count = MAX) : 
				(this.count = (++this.count) % MAX);
	}
	
	public void setCount(int cnt) {
		if (cnt > 0 && cnt <= MAX)
			this.count = cnt;
		else if (cnt > MAX && cnt % MAX != 0)
			this.count = cnt % MAX;
		else if (cnt > MAX && cnt % MAX == 0)
			this.count = MAX;
		else
			this.count = 0;
	}
	
	public void clearCount() {
		this.count = 0;
	}
	
	public int getCount() {
		return this.count;
	}

}
