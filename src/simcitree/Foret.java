package simcitree;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Random;

public class Foret {

    private final ArrayList<Arbre> list = new ArrayList<>();
    private final double rayonDispersion;
    private final double rayonCompetition;
    private final double tauxNaissance;
    private final double tauxMort;
    private final Random random = new Random();


    public Foret(double rayonDispersion, double rayonCompetition, double tauxNaissance, double tauxMort, int nbArbre) {
        this.rayonDispersion = rayonDispersion;
        this.rayonCompetition = rayonCompetition;
        this.tauxNaissance = tauxNaissance;
        this.tauxMort = tauxMort;
        initAllTree(nbArbre);
    }

    public ArrayList<Arbre> getList() {
        return list;
    }

    private void addArbre(double coordonneeX, double coordonneeY) {
        Arbre arbreAdd = new Arbre(coordonneeX,coordonneeY);
        list.add(arbreAdd);
        checkVoisins(arbreAdd);
        Main.serie.getData().add(new XYChart.Data<>(coordonneeX, coordonneeY));
    }

    private void deleteArbre(int index) {
        removeVoisin(index);
        list.remove(list.get(index));
        Main.serie.getData().remove(index);
    }

    private void addFils(int index) {

        Arbre arbrePere = list.get(index);
        double angle = Math.toRadians(random.nextDouble() * 360);
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

    private void initAllTree(int nbArbre) {
        for (int i = 0; i<nbArbre; i++)
            addArbre(Math.random(),Math.random());
    }


    public void applyEvent(int nbEvent){
        double totB = getTauxNaissance()* list.size(); //total Chances Naissance
        double totM = getTauxMort()* list.size(); // total Chances Mort
        //double totC
        double tot = totB+totM;
        double rdm = Math.random()*tot; // entre 0 et 1, il faut alors le rammener sur le total

        int indexArbreRandom = random.nextInt(list.size());

        if (rdm <= totB || nbEvent == 0) { //jusqu'à totB,
            addFils(indexArbreRandom);
        } else if(totB <= rdm && rdm <= totB+totM){ //de totB au total
            deleteArbre(indexArbreRandom);
        }

    }

    public void checkVoisins(Arbre arbre) {
        double rayon = Math.sqrt(rayonCompetition);
        double intensite = 0;
        for (Arbre arbreCourant : list) {
            if (arbreCourant != arbre) {
                double coordArbreCourantX = arbreCourant.getX();
                double coordArbreCourantY = arbreCourant.getY();
                double distance = Math.hypot((coordArbreCourantX - arbre.getX()), (coordArbreCourantY - arbre.getY()));
                if (distance <= rayon) {
                    arbre.addVoisin(arbreCourant);
                    intensite += distance;
                }
            }
        }
        arbre.setIntensiteCompetition(intensite);
    }

    public void removeVoisin(int index) {
        for (Arbre arbreCourant : list) {
            if (arbreCourant.getVoisins().contains(list.get(index))) {
                arbreCourant.resuireIntensiteCompetition(1/(Math.hypot((arbreCourant.getX() - list.get(index).getX()), (arbreCourant.getY() - list.get(index).getY()))));
                arbreCourant.getVoisins().remove(list.get(index));
            }
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
        return tauxNaissance;
    }

    private double getTauxMort(){
        return tauxMort;
    }

    private double getTauxGlobal() {
        return (getTauxNaissance()+getTauxMort()) * list.size();
    }

    public double getDureeNextEven(){
        return  -Math.log(random.nextFloat())
                / getTauxGlobal();
    }
}
