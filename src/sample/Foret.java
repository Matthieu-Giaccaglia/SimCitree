package sample;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Foret {

    private int nbTour;
    private ArrayList<Arbre> list = new ArrayList<>();


    public Foret(int nbTour) {
        this.nbTour = nbTour;
    }

    public int getNbTour() {
        return nbTour;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }

    public ArrayList<Arbre> getList() {
        return list;
    }

    public void addArbre(Arbre arbreAjoute) {
        list.add(arbreAjoute);

        Main.serie.getData().add(new XYChart.Data(arbreAjoute.getX(),arbreAjoute.getY()));

        /*ImageView imageView = new ImageView(new Image(getClass().getResource("raw/arbre.jpg").toExternalForm()));
        imageView.setFitWidth(39);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        GridPane.setHalignment(imageView,HPos.CENTER);*/
    }

    public void deleteArbre(int index) {
        list.remove(list.get(index));
    }

    public void addFils(Arbre arbrePere) {


        double angle = Math.toRadians(Math.random() * 360);
        double amount = arbrePere.getRayonDispersion();
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

        Arbre arbreFils = new Arbre(
                coordonneX,
                coordonneY,
                arbrePere.getRayonCompetition(),
                arbrePere.getRayonDispersion(),
                arbrePere.getEsperanceVie(),
                arbrePere.getIntensiteCompetition(),
                arbrePere.getChanceReproduction());

        addArbre(arbreFils);

    }

    public void listToString() {
        StringBuilder listString = new StringBuilder();

        for (Arbre a : list) {
            listString.append(a.toString()).append("\t");
        }

        System.out.println(listString);
    }
}
