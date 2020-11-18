package simcitree;

import java.util.ArrayList;

public class Arbre {


    private final double x;
    private final double y;
    private double intensiteCompetition;
    private final ArrayList<Voisins> listVoisins = new ArrayList<>();

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

    public void augmenterIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition += intensiteCompetition;
    }

    public void reduireIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition -= intensiteCompetition;
    }

    public void addVoisin(Arbre arbreVoisin, double tauxCompetition) {
        listVoisins.add(new Voisins(arbreVoisin, tauxCompetition));
        augmenterIntensiteCompetition(tauxCompetition);
    }

    public Arbre getArbreVoisins(int i) {
        return listVoisins.get(i).getArbre();
    }

    public void deleteVoisin(Arbre arbreVoisin, double tauxComp) {
        Voisins voisinTrouve = null;
        int index = 0;
        for (int i = 0; i< listVoisins.size(); i++) {
            Voisins voisinsCourant1 = listVoisins.get(i);
            if (voisinsCourant1.getArbre() == arbreVoisin && voisinsCourant1.getTauxComp() == tauxComp) {
                voisinTrouve = voisinsCourant1;
                index = i;
                break;
            }
        }

        System.out.println("index : " + index);
        assert voisinTrouve != null;
        double intensiteComp = voisinTrouve.getTauxComp();
        reduireIntensiteCompetition(intensiteCompetition);
        listVoisins.remove(index);
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

    public ArrayList<Voisins> getListVoisins() {
        return listVoisins;
    }

}
