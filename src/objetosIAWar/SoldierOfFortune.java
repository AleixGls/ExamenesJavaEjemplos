package objetosIAWar;

public abstract class SoldierOfFortune implements Comparable<SoldierOfFortune> {
    private int experience;
    private int cost;
    private BulletproofVest bullet_proof_vest;
    private GrenadeLauncher grenade_launcher;
    private String name;

    
    
    public SoldierOfFortune(String name, int experience, BulletproofVest bullet_proof_vest, int cost) {
        this.name = name;
        this.experience = experience;
        this.bullet_proof_vest = bullet_proof_vest;
        this.cost = cost;
    }

    
    
    public int getCost() {
        int total = cost;
        if (bullet_proof_vest != null) total += bullet_proof_vest.getCost();
        if (grenade_launcher != null) total += grenade_launcher.getCost();
        return total;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public GrenadeLauncher getGrenade_launcher() {
        return grenade_launcher;
    }

    public void setGrenade_launcher(GrenadeLauncher grenade_launcher) {
        this.grenade_launcher = grenade_launcher;
    }

    public String getName() {
        return name;
    }

    public BulletproofVest getBullet_proof_vest() {
        return bullet_proof_vest;
    }

    @Override
    public int compareTo(SoldierOfFortune other) {
        return Integer.compare(this.getCost(), other.getCost());
    }

    @Override
    public String toString() {
        String dataSoldier = Main.header("=", 70, "Soldier " + name) + "\n"
                + String.format("%-30s%20s%20s%n", "Name", "Experience", "Cost")
                + String.format("%-30s%20d%20d%n", name, experience, cost);

        String dataVest = Main.header("-", 70, "Bullet Proof Vest") + "\n"
                + String.format("%-50s%20s%n", "Description", "Cost");
        if (bullet_proof_vest != null) {
            dataVest += String.format("%-50s%20d%n", bullet_proof_vest.getDescription(),
                    bullet_proof_vest.getCost());
        }

        return dataSoldier + "\n" + dataVest;
    }

    public abstract void addHandGrenades(HandGrenades hg);
    public abstract void removeHandGrenades(int position);
    public abstract void showAmmunitionStats();
}
