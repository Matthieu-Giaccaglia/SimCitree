package simcitree.forest;

import java.util.ArrayList;

public class Arbre {


    private final double x;
    private final double y;
    private double intensiteCompetition = 0;
    private final ArrayList<Voisin> listVoisins = new ArrayList<>();

    public Arbre(double x, double y) {
        this.x = x;
        this.y = y;
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
        this.intensiteCompetition = Math.round( (this.intensiteCompetition + intensiteCompetition) * 1000000000) / 1000000000d;
    }

    private void reduireIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition = Math.round( (this.intensiteCompetition - intensiteCompetition) * 1000000000) / 1000000000d;
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
