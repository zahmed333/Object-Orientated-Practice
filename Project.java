import java.util.*;
import java.lang.Math;

public class Project {
	
	private String name;
	private int sqrft;
	private int days;
	private ArrayList<Worker> workerList;
	private int stashL = 0;
	private int stashS = 0;
	private int n_sqrft;
	private int n_days = 0;
	
	private int cacheL = 0;
	private int cacheS = 0;
	private int cachesqrft = 0;

	//The Project class represents a construction project, and contains the method to run a simulation to determine the
	//viability of completing a building by the specified deadline. Each project has a number of square feet, a collection of
	//workers, and a duration.
	
	public Project(String name, int sqrft, int days) {
		workerList = new ArrayList<Worker>();
		this.name = name;
		if (sqrft < 0) {
			this.sqrft = 15000;
		}
		else {
			this.sqrft = sqrft;
		}
		if (days < 0) {
			this.days = 100;
		}
		else {
			this.days = days;
		}
		n_sqrft = this.sqrft;
	}
	
	//simple getter function for testing
	public ArrayList<Worker> getWorkers() {
		return workerList;
	}
	
	public boolean recruitWorker(Worker w) {
		for (int i = 0; i < workerList.size(); i++) {
			if (w != null && w.equals(workerList.get(i))) {
				return false;
			}
		}
		workerList.add(w);
		return true;
	}
	
	public boolean dismissWorker(Worker w) {
		if (w != null && w.equals(w) && workerList.contains(w)) {
			workerList.remove(w);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// Transfers the materials for the day to the overall stash
	public void transferMaterials() {
		stashL += cacheL;
		stashS += cacheS;
		n_sqrft -= cachesqrft;
		cacheL = 0;
		cacheS = 0;
		cachesqrft = 0;
	}
	
	//Checks throughout the code to see for multiples cases and responds
	//accordingly for the cases
	//e.g. if stash is 0 returns 0 built
	public void constructionChecker(int val) {
		if (stashL > 0 && stashS > 0) {
			if (stashL - val > 0 && stashS - val > 0) {
				System.out.println("   ...built " + val + " square feet!");
				cachesqrft += val; stashL -= val; stashS -= val;
			}
			else {
				if (stashL > stashS) {
						System.out.println("   ...built " + stashS + " square feet!");
						cachesqrft += stashS; stashS -= stashS; stashL -= stashS;
				}
				else {
						System.out.println("   ...built " + stashL + " square feet!");
						cachesqrft += stashL; stashS -= stashL; stashL -= stashL;
				}
			}
		}
		else {
			System.out.println("   ...built 0 square feet!");
		}
	}
	
	//checks the instance of each worker and returns what to do for those workers
	public void whichWorker(Worker Workr, int val) {
		if (val > n_sqrft) {
			val = n_sqrft;
		}
		if (Workr instanceof ConstructionWorker) {
			constructionChecker(val);
		}
		else if (Workr instanceof WoodWorker) {
			System.out.println("   ...logged " + val + " logs!");
			cacheL += val;
		}
		else if (Workr instanceof StoneWorker) {
			System.out.println("   ...mined " + val + " stones!");
			cacheS += val;
	 }
	}
	
	//The main daily routine that is repeated and the print statements along with
	// updating mood
	public void dailyRoutine() {
		System.out.println("\n*** Day " + n_days + " ***");
		System.out.println("--- Starting Totals --- Stash: "+ stashL +" logs and " 
			+ stashS + " stone, " + n_sqrft + "sq feet left to build!");
		for (int i = 0; i < workerList.size(); i++) {
			workerList.get(i).updateMood();
			System.out.println(workerList.get(i));
			whichWorker(workerList.get(i), workerList.get(i).work());
		}
		System.out.println("--- Day Totals --- " + cacheL + " logs and "
			+ cacheS + " stone gained, " + cachesqrft + " square feet built!");
	}
			
	//runs the simulation from all 4 helpers with the while loop!!!
	public boolean runSimulation() {
		System.out.println("Beginning " + name + " simulation...");
		while (n_days < days && n_sqrft > 0) {
			n_days++;
			dailyRoutine();
			transferMaterials();
			}
		if (n_sqrft == 0) {
			System.out.println("The " + name +
				" completed successfully after " + n_days + " days!");
			return true;
		}
		//System.out.println(n_sqrft);
		System.out.println("\n" + name + " was incomplete after the maximum of "
			+ days + " days");
		return false;
		}
}