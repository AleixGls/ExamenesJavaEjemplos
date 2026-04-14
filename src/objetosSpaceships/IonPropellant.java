package objetosSpaceships;

public class IonPropellant extends Propeller implements HiperSpaceJump{
	private String description;
	
	
	
	IonPropellant(String description , int power, int cost){
		super(power, cost);
		this.description = description;
	}
	
	
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		String data = "Ionic Propellant" + "\n" + getDescription() + "\n" + "Power: " + getPower() + " W" + "\n" + "Cost: " + getCost() + " U" + "\n" + "Special Feature: Hiper Space Jump";
		return data;
	}
}
