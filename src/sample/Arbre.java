package sample;

import java.util.ArrayList;

public class Arbre {


    private double x, y;
    private double rayonCompetition;
    private double esperanceVie;
    private double rayonDispersion;
    private double intensiteCompetition;
    private double chanceReproduction;
    private ArrayList<Arbre> voisins = new ArrayList<>();

    public Arbre(double x, double y, double rayonCompetition,double rayonDispersion, double esperanceVie, double intensiteCompetition, double chanceReproduction) {
        this.x = x;
        this.y = y;
        this.rayonCompetition = rayonCompetition;
        this.esperanceVie = esperanceVie;
        this.rayonDispersion = rayonDispersion;
        this.intensiteCompetition = intensiteCompetition;
        this.chanceReproduction = chanceReproduction;
    }

    public double getX() {
        return x;
    }


    public void setX(double x) {
        this.x = x;
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {

        this.y = y;
    }

    public double getRayonCompetition() {
        return rayonCompetition;
    }

    public void setRayonCompetition(double rayonCompetition) {
        this.rayonCompetition = rayonCompetition;
    }

    public double getEsperanceVie() {
        return esperanceVie;
    }

    public void setEsperanceVie(double esperanceVie) {
        this.esperanceVie = esperanceVie;
    }

    public double getRayonDispersion() {
        return rayonDispersion;
    }

    public void setRayonDispersion(double rayonDispersion) {
        this.rayonDispersion = rayonDispersion;
    }

    public double getIntensiteCompetition() {
        return intensiteCompetition;
    }

    public void setIntensiteCompetition(double intensiteCompetition) {
        this.intensiteCompetition = intensiteCompetition;
    }

    public double getChanceReproduction() {
        return chanceReproduction;
    }

    public ArrayList<Arbre> getVoisins() {
        return voisins;
    }

    public void setChanceReproduction(double chanceReproduction) {
        this.chanceReproduction = chanceReproduction;
    }

    @Override
    public String toString() {
        return "Arbre{" +
                "x=" + x +
                ", y="+ y  +
                ", rayonCompetition=" + rayonCompetition +
                ", esperanceVie=" + esperanceVie +
                ", rayonDispersion=" + rayonDispersion +
                ", intensiteCompetition=" + intensiteCompetition +
                ", chanceReproduction=" + chanceReproduction +
                '}';
    }


}
