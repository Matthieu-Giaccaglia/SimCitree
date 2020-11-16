package simcitree;

import java.util.ArrayList;

public class Arbre {


    private final double x;
    private final double y;
    private double intensiteCompetition;
    private final ArrayList<Arbre> voisins = new ArrayList<>();
    private final ArrayList<Double> intensiteVoisins = new ArrayList<>();

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

    public void addVoisin(Arbre arbreVoisin, double tauxCompetition) {
        voisins.add(arbreVoisin);
        intensiteVoisins.add(tauxCompetition);
        augmenterIntensiteCompetition(tauxCompetition);
    }

    public void deleteVoisin(Arbre arbreVoisin) {
        intensiteVoisins.remove(voisins.indexOf(arbreVoisin));
        voisins.remove(arbreVoisin);
    }

    public double getDistance(int index) {
        return intensiteVoisins.get(index);
    }


    @Override
    public String toString() {
        return "Arbre{" +
                "x=" + x +
                ", y="+ y  +
                '}';
    }

}
