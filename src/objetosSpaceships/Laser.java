package objetosSpaceships;

public class Laser extends Weapon {
	private String description;
	private LaserGenerator laserGenerator;
	
	
	
	Laser(String description, int attackPower, int cost, LaserGenerator laserGenerator){
		super(attackPower, cost);
		this.description = description;
		this.laserGenerator =  laserGenerator;
		super.setAmmunition(this.laserGenerator);
	}
	
	
	
	public String getDescription() {
		return description;
	}

	public LaserGenerator getLaserGenerator() {
		return laserGenerator;
	}
		
	public String toString() {
		String data = getDescription() + "\n" + "Attack Power: " + getAttackPower() + "\n" + "Cost: " + getCost();
		return data;
	}
}

