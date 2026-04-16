package objetosIATank;

public abstract class Weapon {
    private int cost;
    private String description;
    private float caliber;

    
    
    public Weapon(String description, float caliber, int cost) {
        this.description = description;
        this.caliber = caliber;
        this.cost = cost;
    }

    
    
    public void getData() {
        System.out.printf("%-50s%10.2f%10d%n", description, caliber, cost);
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
}