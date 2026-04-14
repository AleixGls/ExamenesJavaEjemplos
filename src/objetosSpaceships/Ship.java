package objetosSpaceships;

public abstract class Ship implements Comparable<Ship>{
	private int shield; 
	private int attackPower; 
	private int cost; 
	private int speed; 
	private Propeller propeller; 
	private String description; 
	private Weapon[] weapons;
	
	
	Ship(int shield, int cost, int speed, int attackPower, Propeller propeller){
		 this.shield = shield;
		 this.cost = cost;
		 this.speed = speed;
		 this.attackPower = attackPower;
		 this.propeller = propeller;
	}
	 
	 
	 
	public int getShield() {
		 return shield;
	}
	 
	public int getCost() {
		int totalCost = 0;
		 
		totalCost += this.cost;
		totalCost += propeller.getCost();
		//recorerrer la lista de weapons
		for (int i = 0; i < weapons.length; i++) {
			
			//sumar las armas si hay
			if (weapons[i] != null) {
				totalCost += weapons[i].getCost();
				
				//sumar la municion si hay
				if (weapons[i].getAmmunition() != null) {
					totalCost += weapons[i].getAmmunition().getCost();
				}
			}
		}
		 
		return totalCost;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setShield(int shield) {
		this.shield = shield;
	}
	
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setWeapons(Weapon[] weapons) {
    	this.weapons = weapons;
    }
	 
	public void addWeapon(Weapon w) {
		for (int i = 0; i < weapons.length; i++) {
			if (weapons[i] == null) {
				weapons[i] = w;
				return;
			}
		}
		System.out.println("Cannot add more weapons");
		return;
	}
	 
	public void removeWeapon(Weapon w) {
		for (int i = 0; i < weapons.length; i++) {
			if (weapons[i] == w) {
				weapons[i] = null;
				return;
			} 
		}
		System.out.println("This Weapon is not Assembled");
	}
	 
	public void removeIndexweapon(int i) {
		if (weapons[i] == null) {
			System.out.println("This slot has not weapon");
		}
	} 
	
	// ESTO ES PARA PODER ACTIVAR EL HIPERJUMP DEL PROPELLER
	public void activateHiperJump() {
	    if (propeller instanceof HiperSpaceJump) {
	        ((HiperSpaceJump) propeller).jumpHiperspace();
	        this.speed = this.speed * 10; // +1000%
	    }
	}
	
    @Override
    public int compareTo(Ship other) {
        return Integer.compare(other.getCost(), this.getCost());
    }
    
    public String toString() {
    	String dataShip = description + "\n" + "Speed: " + speed + "\n" + "Attack Power: " + attackPower;
    	
    	String dataPropeller = "PROPELLER:" + "\n" + propeller.toString();
    	
    	String dataWeapons = "WEAPONS:" + "\n";;
    	//Recorrer la lista de armas
    	for (int i = 0; i < weapons.length; i++) {
    		
    		//sumar el string de datos de las armas si hay
    		if (weapons[i] != null) {
    			
    			dataWeapons += weapons[i].toString() + "\n";
    			
    			//sumar el string de datos de las municiones si hay
    			if (weapons[i].getAmmunition() != null) {
    				dataWeapons += "Ammunition:" + "\n"; 
    				dataWeapons += weapons[i].getAmmunition().toString() + "\n" + "\n" ;
    			}
    		}
    	}
    	
    	String dataFull = dataShip + "\n"+"\n" + dataPropeller + "\n"+"\n" + dataWeapons + "TOTAL COST: " + getCost();
    	return dataFull;
    }
}
