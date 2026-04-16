package objetosIAWar;

public class Infantry extends SoldierOfFortune {
    private SubMachineGun sub_machine_gun;
    private HandGrenades[] hand_grenades;
    private SubMachineGunBullet[] sub_machine_gun_bullet;

    
    
    public Infantry(String name, int experience, BulletproofVest bullet_proof_vest, int cost) {
        super(name, experience, bullet_proof_vest, cost);
        this.hand_grenades = new HandGrenades[5];
        this.sub_machine_gun_bullet = new SubMachineGunBullet[10];
    }

    
    
    public void setSubMachineGun(SubMachineGun smg_gun) {
        this.sub_machine_gun = smg_gun;
    }

    public void addSubMachineAmmunition(SubMachineGunBullet smgb) {
        if (sub_machine_gun == null) {
            System.out.println("First Assemble a Sub Machine Gun");
            return;
        }
        if (sub_machine_gun.getCaliber() != smgb.getCaliber()) {
            System.out.println("Calibers does not match");
            return;
        }
        for (int i = 0; i < sub_machine_gun_bullet.length; i++) {
            if (sub_machine_gun_bullet[i] == null) {
                sub_machine_gun_bullet[i] = smgb;
                return;
            }
        }
        System.out.println("There no free slots to add more sub machine gun bullets");
    }

    public void removeSubMachineAmmunition(int position) {
        if (position < 0 || position >= sub_machine_gun_bullet.length || sub_machine_gun_bullet[position] == null) {
            System.out.println("There is not Ammunition in this Slot");
            return;
        }
        sub_machine_gun_bullet[position] = null;
    }

    @Override
    public void addHandGrenades(HandGrenades hg) {
        if (getGrenade_launcher() == null) {
            System.out.println("First Equip a Hand Grenade Launcher");
            return;
        }
        if (getGrenade_launcher().getCaliber() != hg.getCaliber()) {
            System.out.println("Calibers does not match");
            return;
        }
        for (int i = 0; i < hand_grenades.length; i++) {
            if (hand_grenades[i] == null) {
                hand_grenades[i] = hg;
                return;
            }
        }
        System.out.println("There are no free slots to add more Hand Grenades");
    }

    @Override
    public void removeHandGrenades(int position) {
        if (position < 0 || position >= hand_grenades.length || hand_grenades[position] == null) {
            System.out.println("There is not Ammunition in this Slot");
            return;
        }
        hand_grenades[position] = null;
    }

    @Override
    public int getCost() {
        int total = super.getCost();
        if (sub_machine_gun != null) total += sub_machine_gun.getCost();
        for (HandGrenades hg : hand_grenades) {
            if (hg != null) total += hg.getCost();
        }
        for (SubMachineGunBullet smgb : sub_machine_gun_bullet) {
            if (smgb != null) total += smgb.getCost();
        }
        return total;
    }

    @Override
    public void showAmmunitionStats() {
        System.out.printf("%-40s%10s%10s%10s%n", "Description", "Amount", "Caliber", "Cost");
        for (HandGrenades hg : hand_grenades) {
            if (hg != null) hg.getData();
        }
        for (SubMachineGunBullet smgb : sub_machine_gun_bullet) {
            if (smgb != null) smgb.getData();
        }
    }

    @Override
    public String toString() {
        // Soldier basic info
        String dataSoldier = Main.header("=", 70, "Soldier " + getName()) + "\n"
                + String.format("%-30s%20s%20s%n", "Name", "Experience", "Cost")
                + String.format("%-30s%20d%20d%n", getName(), getExperience(),
                        super.getCost() - (getBullet_proof_vest() != null ? getBullet_proof_vest().getCost() : 0)
                                - (getGrenade_launcher() != null ? getGrenade_launcher().getCost() : 0));

        // Bulletproof vest
        String dataVest = Main.header("-", 70, "Bullet Proof Vest") + "\n"
                + String.format("%-50s%20s%n", "Description", "Cost");
        if (getBullet_proof_vest() != null) {
            dataVest += String.format("%-50s%20d%n", getBullet_proof_vest().getDescription(),
                    getBullet_proof_vest().getCost());
        }

        // Weapons
        String dataWeapons = Main.header("-", 70, "Weapons") + "\n"
                + String.format("%-50s%10s%10s%n", "Description", "Caliber", "Cost");
        if (getGrenade_launcher() != null) {
            dataWeapons += String.format("%-50s%10.2f%10d%n", getGrenade_launcher().getDescription(),
                    getGrenade_launcher().getCaliber(), getGrenade_launcher().getCost());
        }
        if (sub_machine_gun != null) {
            dataWeapons += String.format("%-50s%10.2f%10d%n", sub_machine_gun.getDescription(),
                    sub_machine_gun.getCaliber(), sub_machine_gun.getCost());
        }

        // Ammunition
        String dataAmmunition = Main.header("-", 70, "Ammunition") + "\n"
                + String.format("%-40s%10s%10s%10s%n", "Description", "Amount", "Caliber", "Cost");
        for (HandGrenades hg : hand_grenades) {
            if (hg != null) {
                dataAmmunition += String.format("%-40s%10d%10.2f%10d%n", hg.getDescription(),
                        hg.getNumberOfShots(), hg.getCaliber(), hg.getCost());
            }
        }
        for (SubMachineGunBullet smgb : sub_machine_gun_bullet) {
            if (smgb != null) {
                dataAmmunition += String.format("%-40s%10d%10.2f%10d%n", smgb.getDescription(),
                        smgb.getNumberOfShots(), smgb.getCaliber(), smgb.getCost());
            }
        }

        String dataTotal = Main.header("-", 70, "Total Cost") + "\n"
                + String.format("%70d%n", getCost());

        return dataSoldier + "\n" + dataVest + "\n" + dataWeapons + "\n" + dataAmmunition + "\n" + dataTotal;
    }
}
