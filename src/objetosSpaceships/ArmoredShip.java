package objetosSpaceships;

public class ArmoredShip extends Ship implements ShieldWeakener{
	private String description;
	private Weapon[] weapons;
	
	
	
	ArmoredShip(String description ,int shield, int cost, int speed, int attackPower , Propeller propeller){
		super(shield, cost, speed, attackPower, propeller);
		this.description = description;
		this.weapons = new Weapon[2];
		super.setDescription(this.description);
		super.setWeapons(this.weapons);		
	}
	
	
	
	public String toString() {
		return "\n====ARMORED SHIP====\n" + super.toString();
	}
}
