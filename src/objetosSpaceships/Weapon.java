package objetosSpaceships;

public class Weapon {
	private int attackPower;
	private int cost; 
	private String description; 
	private Ammunition ammunition;
	
	
	
	Weapon(int attackPower, int cost){
		this.attackPower = attackPower;
		this.cost = cost;
	}
	
	
	
	public int getAttackPower() {
		return attackPower;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Ammunition getAmmunition() {
		return ammunition;
	}
	
	public void setAmmunition(Ammunition ammunition) {
		this.ammunition = ammunition;
	}
	
	public String toString() {
		return "\n" + description + "\n" + "Attack Power: " + attackPower + "\n" + "Cost: " + cost;
	}
	
	
}