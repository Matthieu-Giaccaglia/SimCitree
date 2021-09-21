package simcitree.forest;

import java.util.ArrayList;

/**
 * Classe Arbre simulant un arbre.
 */
public class Arbre {


    /**
     * Axes des abscisses.
     */
    private final double x;
    /**
     * Axes des ordonnées.
     */
    private final double y;
    /**
     * L'instansité de compétition.
     * Aura une influence sur la durée de vie de l'arbre.
     */
    private double intensiteCompetition = 0;
    /**
     * Sa liste d'arbre voisin.
     */
    private final ArrayList<Voisin> listVoisins = new ArrayList<>();

    /**
     * Le constructeur a uniquement besoin des coordonnées de l'arbre pour le créer.
     * @param x Axe des abscisses.
     * @param y Axe des ordonnées.
     */
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

    public double getTauxComp(int index) {
        return listVoisins.get(index).getTauxComp();
    }

    public ArrayList<Voisin> getListVoisins() {
        return listVoisins;
    }


    /**
     * Augmente l'intensité de compétition par une valeur déterminée.
     * @param intensiteCompetition valeur a ajouté sur l'intensité de compétition
     */
    private void augmenterIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition = Math.round( (this.intensiteCompetition + intensiteCompetition) * 1000000000) / 1000000000d;
    }

    /**
     * réduit l'intensité de compétition par une valeur déterminée.
     * @param intensiteCompetition valeur a réduire sur l'intensité de compétition
     */
    private void reduireIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition = Math.round( (this.intensiteCompetition - intensiteCompetition) * 1000000000) / 1000000000d;
    }

    /**
     * Ajoute un voisin.
     * @param voisin le voisin.
     */
    public void addVoisin(Voisin voisin) {
        listVoisins.add(voisin);
        augmenterIntensiteCompetition(voisin.getTauxComp());
    }

    /**
     * Supprime un voisin.
     * @param voisinDelete voisin à supprimer.
     */
    public void deleteVoisin(Voisin voisinDelete) {
        reduireIntensiteCompetition(voisinDelete.getTauxComp());
        listVoisins.remove(voisinDelete);
    }

    @Override
    public String toString() {
        return "Arbre{" +
                "x=" + x +
                ", y="+ y  +
                '}';
    }

}
