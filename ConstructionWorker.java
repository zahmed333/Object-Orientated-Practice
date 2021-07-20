import java.util.*;
import java.lang.Math;

public class ConstructionWorker extends Worker {
	
	private int low;
	private int medium;
	private int high;
	
	public ConstructionWorker(String name, int low, int medium, int high) {
		super(name);
		if (low < 0 || low > medium || medium > high || low > high) {
			this.low = 50;
			this.medium = 100;
			this.high = 150;
		}
		else {
			this.low = low;
			this.medium = medium;
			this.high = high;
	 }
	}
	
	public ConstructionWorker(String name) {
		super(name);
		this.low = 50;
		this.medium = 100;
		this.high = 150;
	}
	
	public String toString() {
		String result = super.toString();
        return "[Construction Worker] " + result + "! (L/M/H per/day "
        + low + "/" + medium + "/" + high + ")";
    }
    
    public int work() {
    int mood = getCurrentMood();
    	if (mood == 0) {
    		return low;
    	}
    	else if (mood == 1) {
    		return medium;
    	}
    	else {
    		return high;
    	}
}

}