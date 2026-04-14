package objetosSpaceships;

public class FusionPropellant extends Propeller{
	private String description;
	
	
	
	FusionPropellant(String description , int power, int cost){
		super(power, cost);
		this.description = description;
	}
	
	
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		String data = "Fusion Propellant" + "\n" + getDescription() + "\n" + "Power: " + getPower() + "\n" + "Cost: " + getCost();
		return data;
	}
}
