package simcitree;

import java.util.ArrayList;

public class Arbre {


    private final double x;
    private final double y;
    private double intensiteCompetition;
    private final ArrayList<Arbre> voisins = new ArrayList<>();

    public Arbre(double x, double y) {
        this.x = x;
        this.y = y;
        this.intensiteCompetition = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getIntensiteCompetition() {
        return intensiteCompetition;
    }

    public ArrayList<Arbre> getVoisins() {
        return voisins;
    }

    public void augmenterIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition += intensiteCompetition;
    }

    public void reduireIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition -= intensiteCompetition;
    }

    public void addVoisin(Arbre arbreVoisin) {
        voisins.add(arbreVoisin);
    }



    @Override
    public String toString() {
        return "Arbre{" +
                "x=" + x +
                ", y="+ y  +
                '}';
    }

}
