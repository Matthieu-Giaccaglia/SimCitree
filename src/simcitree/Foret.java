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
        for (int i = 0; i<nbArbre; i++) {
            addArbre(Math.random(),Math.random());
        }
        for (int i = 0; i < nbArbre; i++) {
            checkVoisins(list.get(i));
        }
        for (int i = 0; i < nbArbre; i++) {
            System.out.println(list.get(i).getVoisins());
        }

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

    private void checkVoisins(Arbre arbre) {
        double rayon = Math.sqrt(rayonCompetition);
        double intensite = 0;
        for (Arbre arbreCourant : list) {
            if (arbreCourant != arbre) {
                double coordArbreCX = arbreCourant.getX();
                double coordArbreCY = arbreCourant.getY();
                double distance = Math.hypot((coordArbreCX - arbre.getX()), (coordArbreCY - arbre.getY()));
                if (!arbre.getVoisins().contains(arbreCourant)) {
                    if (distance <= rayon ) {
                        arbre.addVoisin(arbreCourant);
                        intensite += distance;
                    }
                    if (coordArbreCX + rayonCompetition > 1 ) {
                        if (coordArbreCY + rayonCompetition > 1) {
                            double distance1 = Math.hypot((coordArbreCX - arbre.getX()-1), (coordArbreCY - arbre.getY())-1);
                            if (distance1 <= rayon) {
                                arbre.addVoisin(arbreCourant);
                                intensite += distance1;
                            }
                        }else if (coordArbreCY - rayonCompetition < 0) {
                            double distance1 = Math.hypot((coordArbreCX - arbre.getX()+1), (coordArbreCY - arbre.getY())+1);
                            if (distance1 <= rayon) {
                                arbre.addVoisin(arbreCourant);
                                intensite += distance1;
                            }
                        }else {
                            double distance1 = Math.hypot((coordArbreCX - arbre.getX()-1), (coordArbreCY - arbre.getY()));
                            if (distance1 <= rayon) {
                                arbre.addVoisin(arbreCourant);
                                intensite += distance1;
                            }
                        }
                    }
                    else if (coordArbreCX - rayonCompetition < 0) {
                        if (coordArbreCY + rayonCompetition > 1) {
                            double distance1 = Math.hypot((coordArbreCX - arbre.getX()+1), (coordArbreCY - arbre.getY()-1));
                            if (distance1 <= rayon) {
                                arbre.addVoisin(arbreCourant);
                                intensite += distance1;
                            }
                        }else if (coordArbreCY - rayonCompetition < 0) {
                            double distance1 = Math.hypot((coordArbreCX - arbre.getX()+1), (coordArbreCY - arbre.getY()+1));
                            if (distance1 <= rayon) {
                                arbre.addVoisin(arbreCourant);
                                intensite += distance1;
                            }
                        }else {
                            double distance1 = Math.hypot((coordArbreCX - arbre.getX()), (coordArbreCY - arbre.getY()+1));
                            if (distance1 <= rayon) {
                                arbre.addVoisin(arbreCourant);
                                intensite += distance1;
                            }
                        }
                    }
                    else if (coordArbreCY + rayonCompetition > 1 ) {
                        double distance1 = Math.hypot((coordArbreCX - arbre.getX()), (coordArbreCY - arbre.getY()-1));
                        if (distance1 <= rayon) {
                            arbre.addVoisin(arbreCourant);
                            intensite += distance1;
                        }
                    }
                    else if (coordArbreCY - rayonCompetition < 0 ) {
                        double distance1 = Math.hypot((coordArbreCX - arbre.getX()), (coordArbreCY - arbre.getY()+1));
                        if (distance1 <= rayon) {
                            arbre.addVoisin(arbreCourant);
                            intensite += distance1;
                        }
                    }
                }
            }
        }
        arbre.setIntensiteCompetition(intensite);
    }

    private void removeVoisin(int index) {
        /**
         * Beaucoup plus rapide car on regarde dans la liste de voisin de l'arbre à détruire.
         */
        for (Arbre arbreVoisin : list.get(index).getVoisins()) {
            arbreVoisin.getVoisins().remove(list.get(index));
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

    public ArrayList<Arbre> getList() {
        return list;
    }
}
