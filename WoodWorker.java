import java.util.*;
import java.lang.Math;

public class WoodWorker extends Worker {
	
	private int avgWood;
	
	public WoodWorker(String name, int avgWood) {
		super(name);
		if (avgWood < 40) {
			this.avgWood = 40;
		}
		else {
			this.avgWood = avgWood;
		}
	}
	
	public WoodWorker(String name) {
		super(name);
		this.avgWood = 40;
	}
	
	public String toString() {
	    String result = super.toString();
        return "[Wood Worker] " + result + "! (avg logs/day: " + avgWood + ")";
    }
    
    public int work() {
    int mood = getCurrentMood();
    int tired = (int) (avgWood * .75);
    int pumped = (int) (avgWood * 1.25);
    	if (mood == 0) {
    		return tired;
    	}
    	else if (mood == 1) {
    		return avgWood;
    	}
    	else {
    		return pumped;
    	}
}

}