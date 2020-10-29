package sample;

import java.util.ArrayList;

public class Foret {

    private int nbTour;
    private int taille;
    private Arbre[][] listeArbre;
    private ArrayList<Arbre> list = new ArrayList<Arbre>();
    //private ?  dureeEntreChaqueTour;
    

    public Foret(int nbTour, int taille) {
        this.nbTour = nbTour;
        this.taille = taille;
        this.listeArbre = new Arbre[taille][taille];
    }

    public int getNbTour(){
        return nbTour;
    }

    public int getTaille(){
        return taille;
    }

    public void addArbre(Arbre arbrePere) {
        boolean arBrePlante = false;
        int x = arbrePere.getX();
        int y = arbrePere.getY();

        while(!arBrePlante) {

            if (listeArbre[x][y] == null) {
                // listeArbre[x][y] = new Arbre();
            } else {
                x = 0;
                y = 0;
            }
        }
    }
}
