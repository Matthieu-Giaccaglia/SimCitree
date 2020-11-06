package sample;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Random;

public class Foret {

    private ArrayList<Arbre> list = new ArrayList<>();
    private final double rayonDispersion;
    private final double rayonCompetition;
    private final double tauxNaissance;
    private final double tauxMort;
    private Random randomEven = new Random();
    private Random randomIndex = new Random();


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

    private void addArbre(double coordonneeX, double coordonneeY) {
        list.add(new Arbre(coordonneeX,coordonneeY));
        Main.serie.getData().add(new XYChart.Data(coordonneeX, coordonneeY));
    }

    private void deleteArbre(int index) {
        list.remove(list.get(index));
        Main.serie.getData().remove(index);
    }

    private void addFils(int index) {

        Arbre arbrePere = list.get(index);
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

        addArbre(coordonneX, coordonneY);
    }

    private void initialiseAllArbre(int nbArbre) {
        for (int i = 0; i<nbArbre; i++)
            addArbre(Math.random(),Math.random());
    }


    public void appliquerEvenement(int nbTour){
        double totB = getTauxNaissance()* list.size(); //total Chances Naissance
        double totM = getTauxMort()* list.size(); // total Chances Mort
        //double totC
        double tot = totB+totM;
        double rdm = Math.random()*tot; // entre 0 et 1, il faut alors le rammener sur le total
        int indexRandom = randomIndex.nextInt(list.size());

        if(rdm <= totB || nbTour == 0){//jusqu'à totB,
            addFils(indexRandom);
        }else if(totB <= rdm && rdm <= totB+totM){//de totB au total
            deleteArbre(indexRandom);
        }

    }


    public void listToString() {
        StringBuilder listString = new StringBuilder();

        for (Arbre a : list) {
            listString.append(a.toString()).append("\t");
        }

        System.out.println(listString);
    }

    private double getTauxNaissance(){
        return 1/(tauxNaissance);
    }

    private double getTauxMort(){
        return (1 / (tauxMort));
    }

    private double getTauxGlobal() {
        return (getTauxNaissance()+getTauxMort())* list.size();
    }

    public double getDureeNextEven(){
        return (-Math.log(Math.random()))/(getTauxGlobal());
    }
}
