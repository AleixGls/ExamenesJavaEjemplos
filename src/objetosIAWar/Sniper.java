package objetosIAWar;

public class Sniper extends SoldierOfFortune {
    private SniperRifle sniper_rifle;
    private HandGrenades[] hand_grenades;
    private SniperBullet[] sniper_bullet;

    
    
    public Sniper(String name, int experience, BulletproofVest bullet_proof_vest, int cost) {
        super(name, experience, bullet_proof_vest, cost);
        this.hand_grenades = new HandGrenades[3];
        this.sniper_bullet = new SniperBullet[10];
    }

    
    
    public void setSniper_rifle(SniperRifle sniper_rifle) {
        this.sniper_rifle = sniper_rifle;
    }

    public SniperRifle getSniper_rifle() {
        return sniper_rifle;
    }

    public void addSniperBullets(SniperBullet sp) {
        if (sniper_rifle == null) {
            System.out.println("First Equip a Sniper Riffle");
            return;
        }
        if (sniper_rifle.getCaliber() != sp.getCaliber()) {
            System.out.println("Calibers does not match");
            return;
        }
        for (int i = 0; i < sniper_bullet.length; i++) {
            if (sniper_bullet[i] == null) {
                sniper_bullet[i] = sp;
                return;
            }
        }
        System.out.println("There are no free slots to add more Sniper Riffle bullets");
    }

    public void removeMachineAmmunition(int position) {
        if (position < 0 || position >= sniper_bullet.length || sniper_bullet[position] == null) {
            System.out.println("There is not Ammunition in this Slot");
            return;
        }
        sniper_bullet[position] = null;
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
        if (sniper_rifle != null) total += sniper_rifle.getCost();
        for (HandGrenades hg : hand_grenades) {
            if (hg != null) total += hg.getCost();
        }
        for (SniperBullet sb : sniper_bullet) {
            if (sb != null) total += sb.getCost();
        }
        return total;
    }

    @Override
    public void showAmmunitionStats() {
        System.out.printf("%-40s%10s%10s%10s%n", "Description", "Amount", "Caliber", "Cost");
        for (HandGrenades hg : hand_grenades) {
            if (hg != null) hg.getData();
        }
        for (SniperBullet sb : sniper_bullet) {
            if (sb != null) sb.getData();
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
        if (sniper_rifle != null) {
            dataWeapons += String.format("%-50s%10.2f%10d%n", sniper_rifle.getDescription(),
                    sniper_rifle.getCaliber(), sniper_rifle.getCost());
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
        for (SniperBullet sb : sniper_bullet) {
            if (sb != null) {
                dataAmmunition += String.format("%-40s%10d%10.2f%10d%n", sb.getDescription(),
                        sb.getNumberOfShots(), sb.getCaliber(), sb.getCost());
            }
        }

        String dataTotal = Main.header("-", 70, "Total Cost") + "\n"
                + String.format("%70d%n", getCost());

        return dataSoldier + "\n" + dataVest + "\n" + dataWeapons + "\n" + dataAmmunition + "\n" + dataTotal;
    }
}