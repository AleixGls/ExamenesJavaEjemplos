package objetosIAWar;

public class BulletproofVest {
    private int cost;
    private String description;

    
    
    public BulletproofVest(String description, int cost) {
        this.description = description;
        this.cost = cost;
    }

    
    
    public void getData() {
        System.out.printf("%-50s%20d%n", description, cost);
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}