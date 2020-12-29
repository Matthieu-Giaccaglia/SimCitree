package simcitree.forest;

import javafx.scene.chart.XYChart;
import simcitree.Main;

import java.util.ArrayList;
import java.util.Random;

public class Foret {

    private final ArrayList<Arbre> list = new ArrayList<>();
    private final double rayonDispersion;
    private final double rayonCompetition;
    private final double tauxNaissance;
    private final double tauxMort;
    private final double tauxIntensiteC;
    private double tauxIntensiteCTotal = 0;
    private final Random random = new Random();
    private final ArrayList<ArrayList<ArrayList<Arbre>>> tableauDivision = new ArrayList<>();
    private int division = 1;


    /**
     *
     * @param rayonDispersion Rayon de dispersition.
     * @param rayonCompetition Rayon de compétition
     * @param tauxNaissance Taux de naissance.
     * @param tauxMort Taux de mortalité.
     * @param tauxIntensiteC Taux de compétition.
     * @param nbArbre Nombre d'arbres.
     */
    public Foret(double rayonDispersion, double rayonCompetition, double tauxNaissance, double tauxMort, double tauxIntensiteC, int nbArbre) {
        this.rayonDispersion = rayonDispersion;
        this.rayonCompetition = rayonCompetition;
        this.tauxNaissance = tauxNaissance;
        this.tauxMort = tauxMort;
        this.tauxIntensiteC = tauxIntensiteC;

        if (rayonCompetition>0) {
            double divisionTest = this.rayonCompetition;
            while (divisionTest < 1) {
                this.division *= 10;
                divisionTest *= 10;
            }
        }

        for (int i = 0; i<division;i++) {
            tableauDivision.add(new ArrayList<>());
            for (int j = 0; j < division; j++)
                tableauDivision.get(i).add(new ArrayList<>());
        }

        initAllTree(nbArbre);
    }

    /**
     * Ajoute un certains nombre d'arbre dans la forêt.
     * @param nbArbre Le nombre d'arbre voulu.
     */
    private void initAllTree(int nbArbre) {
        for (int i = 0; i<nbArbre; i++)
            addArbre(random.nextDouble(), random.nextDouble());
    }


    /*
      Naissance/Ajout d'un arbre dans la forêt.
     */

    /**
     * Ajoute un arbre aux coordonnées spécifiées.
     * @param coordonneeX Abscisses
     * @param coordonneeY Ordonnés
     */

    public void addArbre(double coordonneeX, double coordonneeY) {
        Arbre arbreAdd = new Arbre(coordonneeX,coordonneeY);
        list.add(arbreAdd);
        tableauDivision.get((int) (coordonneeX * division)).get((int) (coordonneeY * division)).add(arbreAdd);
        if (rayonCompetition>0 && rayonCompetition <= 0.4) {
            checkVoisinFast(arbreAdd);
        }
        else if(rayonCompetition>0 && rayonCompetition>0.4) {
            checkVoisinsSlow(arbreAdd);
        }

        Main.serie.getData().add(new XYChart.Data<>(coordonneeX, coordonneeY));
    }

    /**
     * Ajoute un arbre via sa naissance provenant d'un autre arbre déjà présent dans la forêt.
     * @param arbrePere Arbre d'où provient le fils.
     */
    private void addFils(Arbre arbrePere) {
        System.out.println("Born");
        double angle = Math.toRadians(random.nextDouble() * 360);
        double coordonneX = (rayonDispersion * Math.cos(angle));
        double coordonneY = (rayonDispersion * Math.sin(angle));

        coordonneX = coordonneX + arbrePere.getX();
        coordonneY = coordonneY + arbrePere.getY();


        if (coordonneX > 1)
            coordonneX = Math.round( (coordonneX - (int) coordonneX) * 10000000 ) / 10000000d;
        else if (coordonneX < 0)
            coordonneX = Math.round( ( Math.abs(coordonneX) - (int) Math.abs(coordonneX) ) * 10000000 ) / 10000000d;

        if (coordonneY > 1)
            coordonneY = Math.round( (coordonneY - (int) coordonneY) * 10000000 ) / 10000000d;
        else if (coordonneY < 0)
            coordonneY = Math.round( ( Math.abs(coordonneY) - (int) Math.abs(coordonneY) ) * 10000000 ) / 10000000d;

        System.out.println("X : " + coordonneX + "; Y : " + coordonneY);
        addArbre(coordonneX, coordonneY);
    }

    /**
     * Permet de trouver plus rapidement les voisins d'un arbre si RC < 0.4.
     * @param arbreNouveau Arbre ajouté à la forêt.
     */
    private void checkVoisinFast(Arbre arbreNouveau){

        double X = arbreNouveau.getX();
        double Y = arbreNouveau.getY();

        //On trouve les coordonnées min et max en fonction de l'arbre et du rayon
        int xmin = (int) ((X - rayonCompetition) * division); //Pour "Diviser" par 10, il faut multiplier par 10
        int xmax = (int) ((X + rayonCompetition) * division);
        int ymin = (int) ((Y - rayonCompetition) * division);
        int ymax = (int) ((Y + rayonCompetition) * division);

        int debordX;
        int debordY;


        //On met mtn dans une liste tous les zones suceptibles de contenir les voisins
        for (int i = xmin; i <= xmax; i ++) {

            debordX = 0;
            int indexX = i;
            //On fait gaffe que ça dépasse pas 1 ou inversement
            if (indexX < 0) {
                indexX += division;
                debordX = 1;
            } else if (indexX >= division) {
                indexX -= division;
                debordX = -1;
            }


            for (int j = ymin; j < ymax; j ++) {

                debordY = 0;
                int indexY = j;
                //On fait gaffe que ça dépasse pas le nombre de case max ou inversement

                if (indexY < 0) {
                    indexY += division;
                    debordY = 1;
                } else if (indexY >= division) {
                    indexY -= division;
                    debordY = -1;
                }

                for (Arbre arbreCourant : tableauDivision.get(indexX).get(indexY))
                    checkInsideRayon(arbreNouveau, arbreCourant, debordX, debordY);

            }
        }

    }

    /**
     * Permet de trouver les voisins de l'arbre ajouté dans la forêt.
     * @param arbre Arbre dont on doit trouver ses voisins.
     */
    private void checkVoisinsSlow(Arbre arbre) {
        double rayon = rayonCompetition;

        for ( Arbre arbreCourant : list) {
            if (arbreCourant != arbre) {

                if (arbre.getX() + rayonCompetition > 1 && arbre.getY() + rayonCompetition > 1)
                    checkInsideRayonTrois(arbre, arbreCourant, -1, -1);
                else if (arbre.getX() + rayonCompetition > 1 && arbre.getY() - rayonCompetition < 0)
                    checkInsideRayonTrois(arbre, arbreCourant, -1, 1);
                else if (arbre.getX() + rayonCompetition > 1 && arbre.getY() + rayonCompetition < 1 && arbre.getY() - rayonCompetition > 0)
                    checkInsideRayon(arbre, arbreCourant, -1,0);

                else if (arbre.getX() - rayonCompetition < 0 && arbre.getY() + rayonCompetition > 1)
                    checkInsideRayonTrois(arbre, arbreCourant, 1, -1);
                else if (arbre.getX() - rayonCompetition < 0 && arbre.getY() - rayonCompetition < 0)
                    checkInsideRayonTrois(arbre, arbreCourant, 1,1);
                else if (arbre.getX() - rayonCompetition < 0 && arbre.getY() + rayonCompetition < 1 && arbre.getY() - rayonCompetition > 0)
                    checkInsideRayon(arbre, arbreCourant, 1, 0);

                else if (arbre.getY() + rayonCompetition > 1)
                    checkInsideRayon(arbre, arbreCourant, 0, -1);
                else if (arbre.getY() - rayonCompetition < 0)
                    checkInsideRayon(arbre, arbreCourant, 0, 1);

                else
                    checkInsideRayon(arbre, arbreCourant, 0, 0);


            }
        }
    }

    /**
     * Permet de savoir si les arbres sont dans le rayon de compétitions de l'autre.
     * @param arbre Arbre dont on doit trouver son voisin.
     * @param arbreCourant Arbre à vérifier s'il est le voisin.
     *
     * @param debordementX
     * <ul> Permet de regarder si les arbres sont voisins par débordement de X ou non du carré.
     *     <li>
     *         - 1 : Si X > 1.
     *     </li>
     *     <li>
     *         0 : S'il n'y a pas de débordement.
     *     </li>
     *     <li>
     *         1 : Si X < 0
     *     </li>
     *</ul>
     * @param debordementY
     * <ul> Permet de regarder si les arbres sont voisins par débordement en Y ou non du carré.
     *      <li>
     *         - 1 : Si Y > 1.
     *      </li>
     *      <li>
     *         0 : S'il n'y a pas de débordement.
     *      </li>
     *      <li>
     *         1 : Si X < 0
     *      </li>
     *</ul>
     */
    private void checkInsideRayon (Arbre arbre , Arbre arbreCourant , int debordementX, int debordementY) {

        double distance = Math.hypot(( (arbre.getX() + debordementX) - arbreCourant.getX()), ( (arbre.getY()+debordementY) - arbreCourant.getY() ));
        if (distance <= rayonCompetition)
            addEachOther(arbre,arbreCourant,distance);
    }

    /**
     * Permet de savoir si les arbres sont dans le rayon de compétitions de l'autre mais en testant de trois manières différentes pour que cela soit plus rapide.
     * @param arbre Arbre dont on doit trouver son voisin.
     * @param arbreCourant Arbre à vérifier s'il est le voisin.
     *
     * @param debordementX
     * <ul> Permet de regarder si les arbres sont voisins par débordement de X ou non du carré.
     *     <li>
     *         - 1 : Si X > 1.
     *     </li>
     *     <li>
     *         0 : S'il n'y a pas de débordement.
     *     </li>
     *     <li>
     *         1 : Si X < 0
     *     </li>
     *</ul>
     * @param debordementY
     * <ul> Permet de regarder si les arbres sont voisins par débordement en Y ou non du carré.
     *      <li>
     *         - 1 : Si Y > 1.
     *      </li>
     *      <li>
     *         0 : S'il n'y a pas de débordement.
     *      </li>
     *      <li>
     *         1 : Si X < 0
     *      </li>
     *</ul>
     */
    private void checkInsideRayonTrois(Arbre arbre , Arbre arbreCourant , int debordementX, int debordementY) {

        double distance1 = Math.hypot(( (arbre.getX() + debordementX) - arbreCourant.getX()), ((arbre.getY() + debordementY) - arbreCourant.getY() ));
        //checkInsideRayon(arbre, arbreCourant, debordementX, debordementY)
        double distance2 = Math.hypot(( (arbre.getX() + debordementX) - arbreCourant.getX()), (arbre.getY()                  - arbreCourant.getY() ));
        //checkInsideRayon(arbre, arbreCourant, debordementX, 0)
        double distance3 = Math.hypot(( arbre.getX()                  - arbreCourant.getX()), ((arbre.getY() + debordementY) - arbreCourant.getY() ));
        //checkInsideRayon(arbre, arbreCourant, 0, debordementY)

        if (distance1 <= rayonCompetition)
            addEachOther(arbre,arbreCourant,distance1);

        else if (distance2 <= rayonCompetition)
            addEachOther(arbre,arbreCourant,distance2);

        else if (distance3 <= rayonCompetition)
            addEachOther(arbre,arbreCourant,distance3);
    }

    /**
     * Permet de lier deux arbres s'ils sont voisins.
     * @param arbre Arbre ajouté dans la forêt.
     * @param arbreCourant Arbre déjà présent dans la forêt.
     * @param distance la distance qui les sépare pour calculer leur intensité de compétition.
     */
    private void addEachOther(Arbre arbre, Arbre arbreCourant, double distance) {
        double tauxCompetition = Math.round((((1-distance) / rayonCompetition) * tauxIntensiteC) * 1000000000) / 1000000000d;
        Voisin voisin = new Voisin(arbre, arbreCourant, tauxCompetition);
        augmenterTauxIntensiteCTotal(tauxCompetition * 2);
    }


    /*
      Mort/Mort par compétition d'un arbre dans la forêt.
     */

    /**
     * Supprime l'arbre de la forêt.
     * @param arbreSupp Arbre à supprimer.
     * @param index L'index de l'arbre dans la liste d'arbre de la forêt. (Utiliser pour l'interface graphique)
     */
    private void deleteArbre(Arbre arbreSupp, int index) {
        System.out.println("Death");

        if (rayonCompetition>0)
            removeVoisin(arbreSupp);

        list.remove(arbreSupp);
        tableauDivision.get((int) (arbreSupp.getX() *division)).get((int) (arbreSupp.getY() *division)).remove(arbreSupp);
        Main.serie.getData().remove(index);
        System.out.println("Fin Death");
    }

    /**
     * Mort par compétition d'un arbre choisi aléatoirement.
     */
    private void deathByCompetition() {
        System.out.println("Death By Competition");
        double tot = 0;int i = 0;
        ArrayList<Double> listCompetitions = new ArrayList<>(list.size());
        double rdm = Math.random()* tauxIntensiteCTotal; // entre 0 et 1, il faut alors le rammener sur le total

        for(Arbre a: list){
            tot += a.getIntensiteCompetition();
            listCompetitions.add(tot);
            if ( rdm < listCompetitions.get(i)) { //jusqu'à totB,
                deleteArbre(list.get(i), i);
                return;
            }
            i++;
        }

    }

    /**
     * Supprime l'arbre mort dans les listes de ses (anciens) voisins.
     * @param arbreDelete Arbre à supprimer des listes.
     */
    private void removeVoisin(Arbre arbreDelete) {

        reduireTauxIntensiteCTotal(arbreDelete.getIntensiteCompetition() * 2);

        for (int i = 0; i<arbreDelete.getListVoisins().size(); i++) {
            arbreDelete.getListVoisins().get(i).delete(arbreDelete);
        }

    }

    /**
     *  Tire un évènement aléatoire parmi :
     *  <ul>
     *      <li>
     *          La naissance.
     *      </li>
     *      <li>
     *          La mort naturelle.
     *      </li>
     *      <li>
     *          La mort par compétition.
     *      </li>
     *  </ul>
     *  Puis applique cet évènement.
     */
    public void applyEvent(){
        double totB = this.tauxNaissance * list.size(); //total Chances Naissance
        double totM = this.tauxMort * list.size(); // total Chances Mort
        double totC = this.tauxIntensiteCTotal;

        double tot = totB+totM+totC;
        double rdm = random.nextDouble()*tot; // entre 0 et 1, il faut alors le rammener sur le total

        int indexArbreRandom = random.nextInt(list.size());

        if (rdm <= totB ) //jusqu'à totB,
            addFils(list.get(indexArbreRandom));

        else if(totB <= rdm && rdm <= totB+totM) //de totB au totB+totM
            deleteArbre(list.get(indexArbreRandom), indexArbreRandom);

        else//de totB+totM au total
            deathByCompetition();

    }

    /**
     * Calcul le taux global de la forêt parmi :
     * <ul>
     *     <li>
     *         Le taux global de naissance.
     *     </li>
     *     <li>
     *         Le taux global de mort naturelle.
     *     </li>
     *     <li>
     *         Le taux global de mort par compétition.
     *     </li>
     * </ul>
     * @return double
     */
    private double getTauxGlobal() {
        return ( (this.tauxNaissance+this.tauxMort) * list.size()) + tauxIntensiteCTotal;
    }

    /**
     * Calcul le temps d'attente avant le prochain évènement.
     * @return double
     */
    public double getDureeNextEvent(){
        double event = -Math.log(random.nextFloat())
                / getTauxGlobal();
        System.out.println("Prochain event dans : " +  event + " sec.");
        return  event;
    }



    /*
      Fonctions annexes
     */
    /**
     * Reduit l'intensité total de compétition de la forêt.
     * @param tauxIntensiteC Valeur à soustraire à l'intensité total de compétition.
     */
    private void reduireTauxIntensiteCTotal (double tauxIntensiteC) {
        tauxIntensiteCTotal = arrondir(this.tauxIntensiteCTotal - tauxIntensiteC);
    }

    /**
     * Augmente l'intensité total de compétition de la forêt.
     * @param tauxIntensiteC Valeur à additionner à l'intensité total de compétition.
     */
    private void augmenterTauxIntensiteCTotal (double tauxIntensiteC) {
        tauxIntensiteCTotal = arrondir(this.tauxIntensiteCTotal + tauxIntensiteC);
    }

    /**
     * Arrondi un nombre.
     * @param nombre nombre à arrondir.
     * @return double
     */
    private double arrondir (double nombre) {
        return Math.round( nombre * 1000000000) / 1000000000d;
    }


    public ArrayList<ArrayList<ArrayList<Arbre>>> getTableauDivision() {
        return tableauDivision;
    }

    public ArrayList<Arbre> getList() {
        return list;
    }











}
