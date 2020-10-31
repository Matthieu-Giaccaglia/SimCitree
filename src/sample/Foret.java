package sample;

import java.util.ArrayList;

public class Foret {

    private int nbTour;
    private int taille;
    private Arbre[][] listeArbre;
    private ArrayList<Arbre> list = new ArrayList<>();
    

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

    public ArrayList<Arbre> getList (){
        return list;
    }

    public void addArbre(Arbre arbrePere) {
        list.add(arbrePere);
        listeArbre[arbrePere.getX()][arbrePere.getY()] = arbrePere;
        listToString();
    }

    public void deleteArbre(int index){
        Arbre arbre = list.get(index);
        listeArbre[arbre.getX()][arbre.getY()] = null;
        list.remove(list.get(index));
        listToString();
    }

    public void addFils(Arbre arbrePere) {
        boolean arbrePlante = false;
        int x = arbrePere.getX();
        int y = arbrePere.getY();

        while(!arbrePlante) {

            if (listeArbre[x][y] == null) {
                // listeArbre[x][y] = new Arbre();
                arbrePlante = true;
            } else {
                x = 0;
                y = 0;
            }
        }
    }

    public void listToString() {
        StringBuilder listString = new StringBuilder();

        for (Arbre a : list)
        {
            listString.append(a.toString()).append("\t");
        }

        System.out.println(listString);
    }
}
