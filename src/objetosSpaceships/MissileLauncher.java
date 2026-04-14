package objetosSpaceships;

public class MissileLauncher extends Weapon{
	private String description;
	private Missile missile;
	
	
	
	MissileLauncher(String description,int attackPower, int cost, Missile missile){
		super(attackPower, cost);
		this.description = description;
		this.missile = missile;
		super.setAmmunition(this.missile);
	}
	
	
	
	public String getDescription() {
		return description;
	}

	public Missile getMissile() {
		return missile;
	}
	
	public String toString() {
		String data = getDescription() + "\n" + "Attack Power: " + getAttackPower() + "\n" + "Cost: " + getCost();
		return data;
	}

}
