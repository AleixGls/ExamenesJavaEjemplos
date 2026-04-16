package objetosIATank;

public abstract class Tank implements Comparable<Tank> {
    private int armor;
    private int cost;
    private int speed;
    private Engine engine;
    private CannonLauncher cannon_launcher;
    private String description;

    
    
    public Tank(String description, int armor, int speed, Engine engine, int cost) {
        this.description = description;
        this.armor = armor;
        this.speed = speed;
        this.engine = engine;
        this.cost = cost;
    }

    
    
    public int getCost() {
        int total = cost;
        if (engine != null) total += engine.getCost();
        if (cannon_launcher != null) total += cannon_launcher.getCost();
        return total;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getSpeed() {
        return speed;
    }

    public CannonLauncher getCannon_launcher() {
        return cannon_launcher;
    }

    public void setCannon_launcher(CannonLauncher cannon_launcher) {
        this.cannon_launcher = cannon_launcher;
    }

    public String getDescription() {
        return description;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public int compareTo(Tank other) {
        return Integer.compare(this.getCost(), other.getCost());
    }

    @Override
    public String toString() {
        String dataTank = Main.header("=", 70, "Tank" + description) + "\n"
                + String.format("%-30s%10s%10s%10s%n", "Description", "Armor", "Speed", "Cost")
                + String.format("%-30s%10d%10d%10d%n", description, armor, speed, cost);

        String dataEngine = Main.header("-", 70, "Engine") + "\n"
                + String.format("%-50s%10s%10s%n", "Description", "Power", "Cost");
        if (engine != null) {
            dataEngine += String.format("%-50s%10d%10d%n", engine.getDescription(),
                    engine.getPower(), engine.getCost());
        }

        return dataTank + "\n" + dataEngine;
    }

    public abstract void addMachineAmmunition(MachineGunBullet mgb);
    public abstract void removeMachineAmmunition(int position);
    public abstract void addCannonProjectile(CannonProjectile cp);
    public abstract void removeCannonProjectile(int position);
    public abstract void showAmmunitionStats();
}