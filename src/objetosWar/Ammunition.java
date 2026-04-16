package objetosWar;

public abstract class Ammunition {
	private String description; 
	private int numberOfShots; 
	private int cost; 
	private float caliber;	
	
	
	
	Ammunition(String description, int numberOfShots, float caliber , int cost) {
		this.description = description;
		this.numberOfShots = numberOfShots;
		this.caliber = caliber;
		this.cost = cost;
	}
	 
	
	
	public String getData() {
		String data = String.format("%-40s%10d%10.2f%10d", description,numberOfShots,caliber,cost);
		return data;
	}
	
	public float getCaliber() {
		return caliber;
	}
	
	public int getCost() {
		return cost;
	}
}