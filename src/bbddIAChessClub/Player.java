package bbddIAChessClub;

public class Player {

    // Atributs
    private int id;
    private String dni;
    private String name;
    private int points;
    private int idPais;

    // Constructor en blanc
    public Player() {
    }

    // Constructor amb tots els atributs
    public Player(int id, String dni, String name, int points, int idPais) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.points = points;
        this.idPais = idPais;
    }

    // Getters i Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public int getIdPais() { return idPais; }
    public void setIdPais(int idPais) { this.idPais = idPais; }

    @Override
    public String toString() {
        return "Player{id=" + id + ", dni='" + dni + "', name='" + name +
               "', points=" + points + ", idPais=" + idPais + "}";
    }
}
