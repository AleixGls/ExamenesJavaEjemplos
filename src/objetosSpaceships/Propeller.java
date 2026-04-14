package objetosSpaceships;

public abstract class Propeller {
	private int power; 
	private int cost; 
	private String description;
	
	
	
	Propeller(int power, int cost){
		this.power = power;
		this.cost = cost;
	}
	
	
	
	public int getPower() {
		return power;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String getDescription() {
		return description;
	}

	public String toString() {
		return "\n" + description + "\n" +"Power: " + power + "\nCost: " + cost;
	}
}
