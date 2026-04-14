package objetosSpaceships;

public class Missile extends Ammunition{
	Missile(String description, int numberOfShots, int cost){
		super(description, numberOfShots, cost);
	}
	
	
	
    public String toString() {
    	String data = getDescription() + "\n" + "Number Of Shots: " + getNumberOfShots() + "\n" + "Cost: " + getCost();
        return data;
    }	 
}