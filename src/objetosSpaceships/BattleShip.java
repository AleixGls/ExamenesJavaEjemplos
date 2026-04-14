package objetosSpaceships;

public class BattleShip extends Ship{
	private String description;
	private Weapon[] weapons;
	
	
	
	BattleShip(String description ,int shield, int cost, int speed, int attackPower , Propeller propeller){
		super(shield, cost, speed, attackPower, propeller);
		this.description = description;
		this.weapons = new Weapon[3];
		super.setDescription(this.description);
		super.setWeapons(this.weapons);		
	}
	
	
	
	public String toString() {
		return "\n====BATTLE SHIP====\n" + super.toString();
	}	
}
