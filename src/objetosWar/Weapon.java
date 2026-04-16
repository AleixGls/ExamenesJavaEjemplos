package objetosWar;

public abstract class Weapon {
	private int cost;
	private String description;
	private float caliber;
	
	
	
	Weapon(String description, float caliber, int cost){
		this.description = description;
		this.caliber = caliber;
		this.cost = cost;
	}
	
	
	
	public String getData() {
		String data = String.format("%-50s%10.2f%10d", description, caliber, cost);
		return data;
	}
	
	public float getCaliber() {
		return caliber;
	}
	
	public int getCost() {
		return cost;
	}
}
