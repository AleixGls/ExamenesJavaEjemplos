package objetosIAWar;

public abstract class Ammunition {
    private String description;
    private int numberOfShots;
    private int cost;
    private float caliber;

    
    
    public Ammunition(String description, int numberOfShots, float caliber, int cost) {
        this.description = description;
        this.numberOfShots = numberOfShots;
        this.caliber = caliber;
        this.cost = cost;
    }

    
    
    public void getData() {
        System.out.printf("%-40s%10d%10.2f%10d%n", description, numberOfShots, caliber, cost);
    }

    public float getCaliber() {
        return caliber;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfShots() {
        return numberOfShots;
    }
}
