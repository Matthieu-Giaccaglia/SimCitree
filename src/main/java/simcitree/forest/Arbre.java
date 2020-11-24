package simcitree.forest;

import java.util.ArrayList;

public class Arbre {


    private final double x;
    private final double y;
    private double intensiteCompetition;
    private final ArrayList<Voisin> listVoisins = new ArrayList<>();

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

    private void augmenterIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition += intensiteCompetition;
    }

    private void reduireIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition -= intensiteCompetition;
    }

    public void addVoisin(Voisin voisin) {
        listVoisins.add(voisin);
        augmenterIntensiteCompetition(voisin.getTauxComp());
    }


    public void deleteVoisin(Voisin voisinDelete) {
        reduireIntensiteCompetition(voisinDelete.getTauxComp());
        listVoisins.remove(voisinDelete);
    }

    public double getTauxComp(int index) {
        return listVoisins.get(index).getTauxComp();
    }


    @Override
    public String toString() {
        return "Arbre{" +
                "x=" + x +
                ", y="+ y  +
                '}';
    }

    public ArrayList<Voisin> getListVoisins() {
        return listVoisins;
    }

}
