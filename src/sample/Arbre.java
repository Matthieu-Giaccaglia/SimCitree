package sample;

public class Arbre {

    private int x, y;
    private int rayonCompetition;
    private double esperanceVie;
    private int rayonDispersion;
    private double intensiteCompetition;
    private double chanceReproduction;

    public Arbre(int x, int y, int rayonCompetition,int rayonDispersion, double esperanceVie, double intensiteCompetition, double chanceReproduction) {
        this.x = x;
        this.y = y;
        this.rayonCompetition = rayonCompetition;
        this.esperanceVie = esperanceVie;
        this.rayonDispersion = rayonDispersion;
        this.intensiteCompetition = intensiteCompetition;
        this.chanceReproduction = chanceReproduction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRayonCompetition() {
        return rayonCompetition;
    }

    public void setRayonCompetition(int rayonCompetition) {
        this.rayonCompetition = rayonCompetition;
    }

    public double getEsperanceVie() {
        return esperanceVie;
    }

    public void setEsperanceVie(double esperanceVie) {
        this.esperanceVie = esperanceVie;
    }

    public int getRayonDispersion() {
        return rayonDispersion;
    }

    public void setRayonDispersion(int rayonDispersion) {
        this.rayonDispersion = rayonDispersion;
    }

    public double getIntensiteCompetition() {
        return intensiteCompetition;
    }

    public void setIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition = intensiteCompetition;
    }

    public double getChanceReproduction() {
        return chanceReproduction;
    }

    public void setChanceReproduction(double chanceReproduction) {
        this.chanceReproduction = chanceReproduction;
    }

    @Override
    public String toString() {
        return "Arbre{" +
                "x=" + x +
                ", y=" + y +
                ", rayonCompetition=" + rayonCompetition +
                ", esperanceVie=" + esperanceVie +
                ", rayonDispersion=" + rayonDispersion +
                ", intensiteCompetition=" + intensiteCompetition +
                ", chanceReproduction=" + chanceReproduction +
                '}';
    }
}
