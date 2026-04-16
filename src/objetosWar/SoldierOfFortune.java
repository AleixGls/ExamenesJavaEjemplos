package objetosWar;

public abstract class SoldierOfFortune implements Comparable<SoldierOfFortune>{
	private int experience; 
	private int cost;
	private BulletproofVest bullet_proof_vest;
	private GrenadeLauncher grenade_launcher; 
	private String name;
	
	
	
	SoldierOfFortune(String name, int experience, BulletproofVest bullet_proof_vest, int cost) {
		this.name = name;
		this.experience = experience;
		this.bullet_proof_vest = bullet_proof_vest;
		this.cost = cost;
	}
	
	
	
	public int getCost() {
		int totalCost = 0;
		
		totalCost += cost;
		totalCost += bullet_proof_vest.getCost();
		totalCost += grenade_launcher.getCost();
		
		return totalCost;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	};
	
	// EN EL PDF LO PONE, PERO NO HAY NINGUN ATRIBUTO ARMOR EN TODO EL PROYECTO
	// public void setArmor(int armor){
	//	this.armor = armor;
	// }
	
	public GrenadeLauncher getGrenade_launcher(){
		return grenade_launcher;
	}
	
	public void setGrenade_launcher(GrenadeLauncher grenade_launcher) {
		this.grenade_launcher = grenade_launcher;
	}
	
    @Override
    public int compareTo(SoldierOfFortune other) {
        return Integer.compare(other.getCost(), this.getCost());
    }
    
    abstract void addHandGrenades(HandGrenades hg);
    abstract void removeHandGrenades(int position);
    abstract void showAmmunitionStats();
    
    public String toString() {
    	String data = "";
    	String columsSoldier = String.format("%-50s%20d", name, cost);
    	String columsBulletProofVest = String.format("%-50s%20d", "Description", cost);
    	
    	data += Main.header("=", 100, "Soldier " + name) + "\n";
    	
    	
    	
    	return data;
    }

}
