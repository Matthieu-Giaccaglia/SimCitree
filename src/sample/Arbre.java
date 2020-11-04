package sample;

import java.util.ArrayList;

public class Arbre {


    private final double x;
    private final double y;
    private double intensiteCompetition;
    private ArrayList<Arbre> voisins = new ArrayList<>();

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

    public void setIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition = intensiteCompetition;
    }

    public void addVoisin(Arbre arbreVoisin) {
        voisins.add(arbreVoisin);
    }

    public ArrayList<Arbre> getVoisins() {
        return voisins;
    }

    @Override
    public String toString() {
        return "Arbre{" +
                "x=" + x +
                ", y="+ y  +
                ", intensiteCompetition=" + intensiteCompetition +
                '}';
    }

}
