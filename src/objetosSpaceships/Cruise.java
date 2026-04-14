package objetosSpaceships;

public class Cruise extends Ship{
	private String description;
	private Weapon[] weapons;
	
	
	
	Cruise(String description, int shield, int cost, int speed, int attackPower ,Propeller propeller){
		super(shield, cost, speed, attackPower, propeller);
		this.description = description;
		this.weapons = new Weapon[1];
		super.setDescription(this.description);
		super.setWeapons(this.weapons);
	}
	
	
	
	public String toString() {
		return "\n====CRUISE====\n" + super.toString();
	}
}
