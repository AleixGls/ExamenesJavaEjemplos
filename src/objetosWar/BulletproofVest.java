package objetosWar;

public class BulletproofVest {
	private int cost; 
	private String description;
	
	
	
	BulletproofVest(String description, int cost){
		this.description = description;
		this.cost = cost;
	}
	
	
	
	public String getData() {
		String data = String.format("%-50s%20d", description, cost);
		return data;
	}
	
	public int getCost() {
		return cost;
	}
}
