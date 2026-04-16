package objetosIATank;

public class LightTank extends Tank {
    private MachineGun machine_gun;
    private CannonProjectile[] cannon_projectile;
    private MachineGunBullet[] machine_gun_bullet;

    
    
    public LightTank(String description, int armor, int speed, Engine engine, int cost) {
        super(description, armor, speed, engine, cost);
        this.cannon_projectile = new CannonProjectile[5];
        this.machine_gun_bullet = new MachineGunBullet[5];
    }

    
    
    public void setMachine_gun(MachineGun machine_gun) {
        this.machine_gun = machine_gun;
    }
    
    @Override
    public void addMachineAmmunition(MachineGunBullet mgb) {
        if (machine_gun == null) {
            System.out.println("First Assemble a Machine Gun");
            return;
        }
        if (machine_gun.getCaliber() != mgb.getCaliber()) {
            System.out.println("Calibers does not match");
            return;
        }
        for (int i = 0; i < machine_gun_bullet.length; i++) {
            if (machine_gun_bullet[i] == null) {
                machine_gun_bullet[i] = mgb;
                return;
            }
        }
        System.out.println("There no free slots to add more machine gun bullets");
    }

    @Override
    public void removeMachineAmmunition(int position) {
        if (position < 0 || position >= machine_gun_bullet.length || machine_gun_bullet[position] == null) {
            System.out.println("There is not Ammunition in this Slot");
            return;
        }
        machine_gun_bullet[position] = null;
    }

    @Override
    public void addCannonProjectile(CannonProjectile cp) {
        if (getCannon_launcher() == null) {
            System.out.println("First Assemble a Cannon Launcher");
            return;
        }
        if (getCannon_launcher().getCaliber() != cp.getCaliber()) {
            System.out.println("Calibers does not match");
            return;
        }
        for (int i = 0; i < cannon_projectile.length; i++) {
            if (cannon_projectile[i] == null) {
                cannon_projectile[i] = cp;
                return;
            }
        }
        System.out.println("There no free slots to add more canon projectils");
    }

    @Override
    public void removeCannonProjectile(int position) {
        if (position < 0 || position >= cannon_projectile.length || cannon_projectile[position] == null) {
            System.out.println("There is not Ammunition in this Slot");
            return;
        }
        cannon_projectile[position] = null;
    }

    @Override
    public int getCost() {
        int total = super.getCost();
        if (machine_gun != null) total += machine_gun.getCost();
        for (CannonProjectile cp : cannon_projectile) {
            if (cp != null) total += cp.getCost();
        }
        for (MachineGunBullet mgb : machine_gun_bullet) {
            if (mgb != null) total += mgb.getCost();
        }
        return total;
    }

    @Override
    public void showAmmunitionStats() {
        System.out.printf("%-40s%10s%10s%10s%n", "Description", "Amount", "Caliber", "Cost");
        for (CannonProjectile cp : cannon_projectile) {
            if (cp != null) cp.getData();
        }
        for (MachineGunBullet mgb : machine_gun_bullet) {
            if (mgb != null) mgb.getData();
        }
    }

    @Override
    public String toString() {
        // Tank basic info
        String dataTank = Main.header("=", 70, "Tank" + getDescription()) + "\n"
                + String.format("%-30s%10s%10s%10s%n", "Description", "Armor", "Speed", "Cost")
                + String.format("%-30s%10d%10d%10d%n", getDescription(), getArmor(), getSpeed(),
                        super.getCost() - (getEngine() != null ? getEngine().getCost() : 0)
                                - (getCannon_launcher() != null ? getCannon_launcher().getCost() : 0));

        // Engine info
        String dataEngine = Main.header("-", 70, "Engine") + "\n"
                + String.format("%-50s%10s%10s%n", "Description", "Power", "Cost");
        if (getEngine() != null) {
            dataEngine += String.format("%-50s%10d%10d%n", getEngine().getDescription(),
                    getEngine().getPower(), getEngine().getCost());
        }

        // Weapons info
        String dataWeapons = Main.header("-", 70, "Weapons") + "\n"
                + String.format("%-50s%10s%10s%n", "Description", "Caliber", "Cost");
        if (getCannon_launcher() != null) {
            dataWeapons += String.format("%-50s%10.2f%10d%n", getCannon_launcher().getDescription(),
                    getCannon_launcher().getCaliber(), getCannon_launcher().getCost());
        }
        if (machine_gun != null) {
            dataWeapons += String.format("%-50s%10.2f%10d%n", machine_gun.getDescription(),
                    machine_gun.getCaliber(), machine_gun.getCost());
        }

        // Ammunition info
        String dataAmmunition = Main.header("-", 70, "Ammunition") + "\n"
                + String.format("%-40s%10s%10s%10s%n", "Description", "Amount", "Caliber", "Cost");
        for (CannonProjectile cp : cannon_projectile) {
            if (cp != null) {
                dataAmmunition += String.format("%-40s%10d%10.2f%10d%n", cp.getDescription(),
                        cp.getNumberOfShots(), cp.getCaliber(), cp.getCost());
            }
        }
        for (MachineGunBullet mgb : machine_gun_bullet) {
            if (mgb != null) {
                dataAmmunition += String.format("%-40s%10d%10.2f%10d%n", mgb.getDescription(),
                        mgb.getNumberOfShots(), mgb.getCaliber(), mgb.getCost());
            }
        }

        String dataTotal = Main.header("-", 70, "Total Cost") + "\n"
                + String.format("%70d%n", getCost());

        return dataTank + "\n" + dataEngine + "\n" + dataWeapons + "\n" + dataAmmunition + "\n" + dataTotal;
    }
}