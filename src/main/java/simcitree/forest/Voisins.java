package simcitree.forest;

public class Voisins {

    private Arbre arbre;
    private double tauxComp;

    public Voisins(Arbre arbre, double tauxComp) {

        this.arbre = arbre;
        this.tauxComp = tauxComp;
    }

    public Arbre getArbre() {
        return arbre;
    }

    public double getTauxComp() {
        return tauxComp;
    }
}
