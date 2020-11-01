package sample;

import java.util.ArrayList;
import java.util.Random;

public class Foret {

    private int nbTour;
    private int taille;
    private Arbre[][] tableauArbre;
    private ArrayList<Arbre> list = new ArrayList<>();
    

    public Foret(int nbTour, int taille) {
        this.nbTour = nbTour;
        this.taille = taille;
        this.tableauArbre = new Arbre[taille][taille];
    }

    public int getNbTour(){
        return nbTour;
    }

    public void setNbTour(int nbTour) { this.nbTour = nbTour;}

    public int getTaille(){
        return taille;
    }

    public ArrayList<Arbre> getList (){
        return list;
    }

    public void addArbre(Arbre arbreAjoute) {
        list.add(arbreAjoute);
        tableauArbre[arbreAjoute.getX()][arbreAjoute.getY()] = arbreAjoute;
        listToString();
    }

    public void deleteArbre(int index){
        Arbre arbre = list.get(index);
        tableauArbre[arbre.getX()][arbre.getY()] = null;
        list.remove(list.get(index));
        listToString();
    }

    public void addFils(Arbre arbrePere) {


        int coordonneX = new Random().nextInt((arbrePere.getRayonDispersion()) + arbrePere.getRayonDispersion() ) - arbrePere.getRayonDispersion();
        int coordonneY = new Random().nextInt((arbrePere.getRayonDispersion()) + arbrePere.getRayonDispersion() ) - arbrePere.getRayonDispersion();

        if (arbrePere.getX()+coordonneX > tableauArbre.length - 1) {
            coordonneX = arbrePere.getX() + coordonneX - tableauArbre.length;
        } else if (arbrePere.getX() + coordonneX < 0) {
            coordonneX = arbrePere.getX() + coordonneX + tableauArbre.length;
        }

        if (arbrePere.getY()+coordonneY > tableauArbre.length - 1) {
            coordonneY = arbrePere.getY() + coordonneY - tableauArbre.length;
        } else if (arbrePere.getY() + coordonneY < 0) {
            coordonneY = arbrePere.getX() + coordonneY + tableauArbre.length;
        }
        if (tableauArbre[coordonneX][coordonneY] == null) {

            Arbre arbreFils = new Arbre(coordonneX,
                     coordonneY,
                     arbrePere.getRayonCompetition(),
                     arbrePere.getRayonDispersion(),
                     arbrePere.getEsperanceVie(),
                     arbrePere.getIntensiteCompetition(),
                     arbrePere.getChanceReproduction());

            addArbre(arbreFils);
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
