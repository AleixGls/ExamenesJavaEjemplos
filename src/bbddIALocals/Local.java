package bbddIALocals;

public class Local {

    // Atributs privats
    private int id;
    private int metres_quadrats;
    private float preu;
    private String tipus; // local_comercial, oficina, traster

    // Constructor en blanc
    public Local() {
    }

    // Constructor amb tots els atributs
    public Local(int id, int metres_quadrats, float preu, String tipus) {
        this.id = id;
        this.metres_quadrats = metres_quadrats;
        this.preu = preu;
        this.tipus = tipus;
    }

    // Getters i Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMetres_quadrats() {
        return metres_quadrats;
    }

    public void setMetres_quadrats(int metres_quadrats) {
        this.metres_quadrats = metres_quadrats;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    // toString sobreescrit
    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", metres_quadrats=" + metres_quadrats +
                ", preu=" + preu +
                ", tipus='" + tipus + '\'' +
                '}';
    }
}