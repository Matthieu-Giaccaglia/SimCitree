package simcitree;


import java.util.ArrayList;
import java.util.Random;

public class Foret {

    private final ArrayList<Arbre> list = new ArrayList<>();
    private final double rayonDispersion;
    private final double rayonCompetition;
    private final double tauxNaissance;
    private final double tauxMort;
    private final double tauxIntensiteC;
    private double sommeIntensiteC;
    private final Random random = new Random();
    private final ArrayList<ArrayList<ArrayList<Arbre>>> tableauDivision = new ArrayList<>();
    private int division = 10;


    public Foret(double rayonDispersion, double rayonCompetition, double tauxNaissance, double tauxMort, int nbArbre) {
        this.rayonDispersion = rayonDispersion;
        this.rayonCompetition = rayonCompetition;
        this.tauxNaissance = tauxNaissance;
        this.tauxMort = tauxMort;
        this.tauxIntensiteC = 0;
        this.sommeIntensiteC = 0;

        initAllTree(nbArbre);
    }


    public void addArbre(double coordonneeX, double coordonneeY) {
        Arbre arbreAdd = new Arbre(coordonneeX,coordonneeY);
        list.add(arbreAdd);
        checkVoisins(arbreAdd);
        sommeIntensiteC += arbreAdd.getIntensiteCompetition();
        //Main.serie.getData().add(new XYChart.Data<>(coordonneeX, coordonneeY));
    }
    public void addArbreV2(double coordonneeX, double coordonneeY) {
        Arbre arbreAdd = new Arbre(coordonneeX,coordonneeY);
        list.add(arbreAdd);
        tableauDivision.get((int) coordonneeX).get((int) coordonneeY).add(arbreAdd);
        checkVoisinsV2(arbreAdd);
        sommeIntensiteC += arbreAdd.getIntensiteCompetition();
        //Main.serie.getData().add(new XYChart.Data<>(coordonneeX, coordonneeY));
    }

    private void deleteArbre(int index) {
        this.sommeIntensiteC -= list.get(index).getIntensiteCompetition();
        removeVoisin(index);
        list.remove(list.get(index));
        //Main.serie.getData().remove(index);
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
            System.out.println(list.get(i).getVoisins());
        }

    }


    public void applyEvent(int nbEvent){
        double totB = this.tauxNaissance; //total Chances Naissance
        double totM = this.tauxMort; // total Chances Mort
        double totC = this.sommeIntensiteC;

        double tot = totB+totM+totC;
        double rdm = Math.random()*tot; // entre 0 et 1, il faut alors le rammener sur le total

        int indexArbreRandom = random.nextInt(list.size());

        if (rdm <= totB || nbEvent == 0) { //jusqu'à totB,
            addFils(indexArbreRandom);
        } else if(totB <= rdm && rdm <= totB+totM){ //de totB au totB+totM
            deleteArbre(indexArbreRandom);
        }else{//de totB+totM au total
            deathByCompetition();
        }

    }

    private void deathByCompetition() {
        int tot = 0;int i = 0;
        ArrayList<Integer> listCompetitions = new ArrayList<>(list.size());
        double rdm = Math.random()*sommeIntensiteC; // entre 0 et 1, il faut alors le rammener sur le total

        for(Arbre a: list){
            tot += a.getIntensiteCompetition();
            listCompetitions.add(tot);
            if (rdm<listCompetitions.get(i)) { //jusqu'à totB,
                deleteArbre(i);
                return;
            }
            i++;
        }

    }

    private void checkVoisinsV2(Arbre arbreNouveau) {
        for (Arbre arbreList : list) {
            if (arbreList != arbreNouveau) {

                if (getDistance(arbreNouveau.getX(), arbreList.getX()-1, arbreNouveau.getY(), arbreList.getY()) <= rayonCompetition

                   || getDistance(arbreNouveau.getX(), arbreList.getX()-1, arbreNouveau.getY(), arbreList.getY()-1) <= rayonCompetition
                   || getDistance(arbreNouveau.getX(), arbreList.getX()-1, arbreNouveau.getY(), arbreList.getY()+1) <= rayonCompetition
                   || getDistance(arbreNouveau.getX(), arbreList.getX()+1, arbreNouveau.getY(), arbreList.getY()) <= rayonCompetition
                   || getDistance(arbreNouveau.getX(), arbreList.getX()+1, arbreNouveau.getY(), arbreList.getY()-1) <= rayonCompetition
                   || getDistance(arbreNouveau.getX(), arbreList.getX()+1, arbreNouveau.getY(), arbreList.getY()+1) <= rayonCompetition
                   || Math.hypot((arbreNouveau.getX() - arbreList.getX() ), (arbreNouveau.getY() - arbreList.getY())) <= rayonCompetition
                   || getDistance(arbreNouveau.getX(), arbreList.getX(), arbreNouveau.getY(), arbreList.getY()-1) <= rayonCompetition
                   || getDistance(arbreNouveau.getX(), arbreList.getX(), arbreNouveau.getY(), arbreList.getY()+1) <= rayonCompetition
                ) {
                    arbreNouveau.addVoisin(arbreList);
                    arbreList.addVoisin(arbreNouveau);
                }
            }
        }
    }

    private double getDistance(double X1, double X2, double Y1, double Y2) {
        double puissX = Math.pow(X2-X1, 2);
        double puissY = Math.pow(Y2-Y1, 2);
        return Math.sqrt(puissX + puissY);
    }

    private ArrayList<ArrayList<Arbre>> diviserTableau(double X, double Y){

        //int division2 = division/10;

        int xmin = (int) (X - rayonCompetition) * division;
        int xmax = (int) (X + rayonCompetition) * division;
        int ymin = (int) (Y - rayonCompetition) * division;
        int ymax = (int) (Y + rayonCompetition) * division;

        ArrayList<ArrayList<Arbre>> returnThis = new ArrayList<>();

        for (int i = xmin; i <= xmax; i= i + 1) {

            int j = i;
            while (j < 0)
                j++;
            while (j>1)
                j--;

            for (int k = ymin; k< ymax; k= k + 1) {

                int l = k;
                while (l<0)
                    l++;
                while (l>1)
                    l--;

                returnThis.add(tableauDivision.get(j).get(l));
            }
        }


        return returnThis;
    }


    private void checkVoisins(Arbre arbre) {
        double rayon = rayonCompetition;
        for (Arbre arbreCourant : list) {
            if (arbreCourant != arbre) {
                double coordArbreCX = arbreCourant.getX();
                double coordArbreCY = arbreCourant.getY();
                double distance =  Math.hypot((arbre.getX() - coordArbreCX ), (arbre.getY() - coordArbreCY));
                checkInsideRayon(arbre, arbreCourant, distance);

                    if (arbre.getX() + rayonCompetition > 1 ) {
                        if (arbre.getY() + rayonCompetition > 1) {

                            double distance1 = Math.hypot((arbre.getX()-1) - coordArbreCX , (arbre.getY())-1 - coordArbreCY);
                            double distance2 = Math.hypot((arbre.getX()-1) - coordArbreCX , (arbre.getY() - coordArbreCY));
                            double distance3 = Math.hypot((arbre.getX() - coordArbreCX ), (arbre.getY()-1) - coordArbreCY);

                            checkInsideRayon(arbre, arbreCourant, distance1, distance2, distance3);

                        }else if (arbre.getY() - rayonCompetition < 0) {

                            double distance1 = Math.hypot((arbre.getX() - coordArbreCX), ((arbre.getY())+1) - coordArbreCY);
                            double distance2 = Math.hypot(((arbre.getX()-1) - coordArbreCX) , (arbre.getY()+1) - coordArbreCY );
                            double distance3 = Math.hypot(((arbre.getX()-1) - coordArbreCX), (arbre.getY() - coordArbreCY));

                            checkInsideRayon(arbre, arbreCourant, distance1, distance2, distance3);

                        }else {
                            double distance1 = Math.hypot(((arbre.getX()-1) -coordArbreCX), ((arbre.getY()) - coordArbreCY ));
                            checkInsideRayon(arbre, arbreCourant, distance1);
                        }
                    }
                    else if (arbre.getX() - rayonCompetition < 0) {
                        if (arbre.getY() + rayonCompetition > 1) {

                            double distance3 = Math.hypot(((arbre.getX()+1) -coordArbreCX), (arbre.getY()-1) - coordArbreCY);
                            double distance1 = Math.hypot(((arbre.getX()+1) - coordArbreCX), (arbre.getY()- coordArbreCY));
                            double distance2 = Math.hypot((arbre.getX()- coordArbreCX), (arbre.getY()-1)-coordArbreCY );

                            checkInsideRayon(arbre, arbreCourant, distance1, distance2, distance3);

                        }else if (arbre.getY() - rayonCompetition < 0) {

                            double distance1 = Math.hypot(((arbre.getX()+1) - coordArbreCX), ((arbre.getY()+1) - coordArbreCY));
                            double distance2 = Math.hypot(( arbre.getX()+1 - coordArbreCX), (arbre.getY() - coordArbreCY));
                            double distance3 = Math.hypot((arbre.getX() - coordArbreCX), ((arbre.getY()+1) - coordArbreCY));

                            checkInsideRayon(arbre, arbreCourant, distance1, distance2, distance3);

                        }else {
                            double distance1 = Math.hypot((arbre.getX() - coordArbreCX), ((arbre.getY()+1) - coordArbreCY));
                            checkInsideRayon(arbre, arbreCourant, distance1);
                        }
                    }
                    else if (arbre.getY() + rayonCompetition > 1 ) {
                        double distance1 = Math.hypot((arbre.getX() - coordArbreCX), (arbre.getY()-1) - coordArbreCY);
                        checkInsideRayon(arbre, arbreCourant, distance1);
                    }
                    else if (arbre.getY() - rayonCompetition < 0 ) {
                        double distance1 = Math.hypot((arbre.getX() - coordArbreCX), ((arbre.getY()+1) - coordArbreCY));
                        checkInsideRayon(arbre, arbreCourant, distance1);
                    }
                }
            }
        }



    private void checkInsideRayon (Arbre arbre , Arbre arbreCourant , double distance) {
        if (distance <= rayonCompetition) {
            arbre.addVoisin(arbreCourant);
            arbreCourant.addVoisin(arbre);
            arbre.setIntensiteCompetition(1);
        }
    }

    private void checkInsideRayon (Arbre arbre , Arbre arbreCourant , double distance, double distance2, double distance3) {
        if (distance <= rayonCompetition || distance2 <= rayonCompetition ||distance3 <= rayonCompetition ) {
            arbre.addVoisin(arbreCourant);
            arbreCourant.addVoisin(arbre);
            arbre.setIntensiteCompetition(1);
        }
    }


    private void removeVoisin(int index) {
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

    private double getTauxGlobal() {
        return (this.tauxNaissance+this.tauxMort) * list.size();
    }

    public double getDureeNextEvent(){
        return  -Math.log(random.nextFloat())
                / getTauxGlobal();
    }

    public ArrayList<Arbre> getList() {
        return list;
    }
}
