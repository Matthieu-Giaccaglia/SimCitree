package simcitree;

import org.junit.Test;
import simcitree.forest.Arbre;
import simcitree.forest.Foret;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ForetTest {
    Foret foret;


    @Test
    public void checkVoisins() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,0);
        foret.addArbre(0.5,0.5);
        foret.addArbre(0.55,0.5);

        Arbre arbre1 = foret.getList().get(foret.getList().size()-2);
        Arbre arbre2 = foret.getList().get(foret.getList().size()-1);
        foret.getList().indexOf(arbre1);
        assertEquals(arbre1.getListVoisins(), arbre2.getListVoisins());
    }

    @Test
    public void checkVoisins_DebordementXandY() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,0);
        foret.addArbre(0,0);
        foret.addArbre(0.9,0.9);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertEquals(arbre1.getListVoisins(), arbre2.getListVoisins());
    }


    @Test
    public void checkVoisins_DebordementX() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,0);
        foret.addArbre(0,0);
        foret.addArbre(0.9,0);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertEquals(arbre1.getListVoisins(), arbre2.getListVoisins());
    }

    @Test
    public void checkVoisins_DebordementY() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0,0);
        foret.addArbre(0,0);
        foret.addArbre(0,0.9);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertEquals(arbre1.getListVoisins(), arbre2.getListVoisins());
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