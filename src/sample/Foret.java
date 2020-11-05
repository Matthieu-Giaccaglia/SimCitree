package sample;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Random;

public class Foret {

    private ArrayList<Arbre> list = new ArrayList<>();
    private final double rayonDispersion;
    private final double rayonCompetition;
    private double tauxNaissance;
    private double tauxMort;


    public Foret(double rayonDispersion, double rayonCompetition, double tauxNaissance, double tauxMort, int nbArbre) {
        this.rayonDispersion = rayonDispersion;
        this.rayonCompetition = rayonCompetition;
        this.tauxNaissance = tauxNaissance;
        this.tauxMort = tauxMort;
        initialiseAllArbre(nbArbre);
    }

    public ArrayList<Arbre> getList() {
        return list;
    }

    public void addArbre(double coordonneeX, double coordonneeY) {
        list.add(new Arbre(coordonneeX,coordonneeY));
        Main.serie.getData().add(new XYChart.Data(coordonneeX, coordonneeY));
    }

    public void deleteArbre(int index) {
        list.remove(list.get(index));
        Main.serie.getData().remove(index);
    }

    public void addFils(Arbre arbrePere) {

        double angle = Math.toRadians(Math.random() * 360);
        double amount = rayonDispersion;
        double coordonneX = (amount * Math.cos(angle));
        double coordonneY = (amount * Math.sin(angle));

        coordonneX = coordonneX + arbrePere.getX();
        coordonneY = coordonneY + arbrePere.getY();

        while (coordonneX >= 1) {
            coordonneX = coordonneX - 1;
        }
        while (coordonneX <= 0) {
            coordonneX = coordonneX + 1;
        }

        while (coordonneY >= 1) {
            coordonneY = coordonneY - 1;
        }
        while (coordonneY <= 0) {
            coordonneY = coordonneY + 1;
        }

        System.out.println(coordonneX +","+ coordonneY);
        addArbre(coordonneX, coordonneY);
    }

    public void initialiseAllArbre(int nbArbre) {
        for (int i = 0; i<nbArbre; i++)
            addArbre(Math.random(),Math.random());
    }


    public void listToString() {
        StringBuilder listString = new StringBuilder();

        for (Arbre a : list) {
            listString.append(a.toString()).append("\t");
        }

        System.out.println(listString);
    }

    public double getTauxNaissance(){
        return 1/(tauxNaissance*list.size());
    }

    public double getTauxMort(){
        return (1 / (tauxMort * list.size()));
    }

    public double getTauxGlobal() {
        return (1/((tauxNaissance + tauxMort)*list.size()));
    }
}
