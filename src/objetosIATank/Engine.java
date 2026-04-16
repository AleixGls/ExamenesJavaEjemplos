package objetosIATank;

public class Engine {
    private int power;
    private int cost;
    private String description;

    
    
    public Engine(String description, int power, int cost) {
        this.description = description;
        this.power = power;
        this.cost = cost;
    }

    
    
    public void getData() {
        System.out.printf("%-50s%10d%10d%n", description, power, cost);
    }

    public int getCost() {
        return cost;
    }

    public int getPower() {
        return power;
    }

    public String getDescription() {
        return description;
    }
}