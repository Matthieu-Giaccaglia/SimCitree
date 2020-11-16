package test;

import org.junit.Test;
import simcitree.Arbre;
import simcitree.Foret;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForetTest {
    Foret foret;

    @Test
    public void checkVoisinsWithoutCase() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,50000);
        foret.addArbre(0.5,0.5);
        foret.addArbre(0.55,0.5);

        Arbre arbre1 = foret.getList().get(foret.getList().size()-2);
        Arbre arbre2 = foret.getList().get(foret.getList().size()-1);
        foret.getList().indexOf(arbre1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }

    @Test
    public void checkVoisins() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,50000);
        foret.addArbreV2(0.5,0.5);
        foret.addArbreV2(0.55,0.5);

        Arbre arbre1 = foret.getList().get(foret.getList().size()-2);
        Arbre arbre2 = foret.getList().get(foret.getList().size()-1);
        foret.getList().indexOf(arbre1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }

    @Test
    public void checkVoisins_DebordementXandY() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,0);
        foret.addArbre(0,0);
        foret.addArbre(0.9,0.9);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }


    @Test
    public void checkVoisins_DebordementX() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,0);
        foret.addArbre(0,0);
        foret.addArbre(0.9,0);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }

    @Test
    public void checkVoisins_DebordementY() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,0);
        foret.addArbre(0,0);
        foret.addArbre(0,0.9);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }


    @Test
    public void checkBonCase(){
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,0);
        foret.addArbre(0.5,0.7);
        foret.addArbre(0.002, 0.415);


        assertTrue(foret.getTableauDivision().get(5).get(7).contains(foret.getList().get(0))
                    && foret.getTableauDivision().get(0).get(4).contains(foret.getList().get(1)));
    }

}