package test;

import org.junit.jupiter.api.Test;
import simcitree.Arbre;
import simcitree.Foret;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForetTest {
    Foret foret;

    @Test
    void checkVoisins() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0);
        foret.addArbre(0.5,0.5);
        foret.addArbre(0.55,0.5);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2));
    }

    @Test
    void checkVoisins_DebordementXandY() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0);
        foret.addArbreV2(0,0);
        foret.addArbreV2(0.9,9);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }

    @Test
    void checkVoisinsV2() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0);
        foret.addArbreV2(0.5,0.5);
        foret.addArbreV2(0.55,0.5);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }

    @Test
    void checkVoisinsV2_DebordementX() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0);
        foret.addArbreV2(0,0);
        foret.addArbreV2(0.9,0);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }

    @Test
    void checkVoisinsV2_DebordementY() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0);
        foret.addArbreV2(0,0);
        foret.addArbreV2(0,0.9);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }

    @Test
    void checkVoisinsV2_DebordementXandY() {
        foret = new Foret(0.5, 0.2, 0.1, 0.1, 0);
        foret.addArbreV2(0,0);
        foret.addArbreV2(0.9,0.9);

        Arbre arbre1 = foret.getList().get(0);
        Arbre arbre2 = foret.getList().get(1);
        assertTrue(arbre1.getVoisins().contains(arbre2) && arbre2.getVoisins().contains(arbre1));
    }

}