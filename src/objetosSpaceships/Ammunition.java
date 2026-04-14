package objetosSpaceships;

public abstract class Ammunition {
	private String description; 
	private int numberOfShots; 
	private int cost;
	
	
	
	Ammunition(String description, int numberOfShots, int cost){
		this.description = description;
		this.numberOfShots = numberOfShots;
		this.cost = cost;
	}
	
	
	
    public String getDescription() {
        return description;
    }
    public int getNumberOfShots() {
        return numberOfShots;
    }
    public int getCost() {
        return cost;
    }
	
	public String toString() {
		return "\n" + description + "\n" +"Number Of Shots: " + numberOfShots + "\nCost: " + cost;
	}
}
