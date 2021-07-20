import java.util.*;
import java.lang.Math;

public class StoneWorker extends Worker {
	
	private int avgStone;
	
	public StoneWorker(String name, int avgStone) {
		super(name);
		if (avgStone < 60) {
			this.avgStone = 60;
		}
		else {
			this.avgStone = avgStone;
		}
	}
	
	public StoneWorker(String name) {
		super(name);
		this.avgStone = 60;
	}
	
	public String toString() {
	    String result = super.toString();
        return "[Stone Worker] " + result + "! (avg stones/day: " + avgStone + ")";
    }
    
    public int work() {
    int mood = getCurrentMood();
    int tired = (int) (avgStone * .50);
    int pumped = (int) (avgStone * 1.50);
    	if (mood == 0) {
    		return tired;
    	}
    	else if (mood == 1) {
    		return avgStone;
    	}
    	else {
    		return pumped;
    	}
}

}