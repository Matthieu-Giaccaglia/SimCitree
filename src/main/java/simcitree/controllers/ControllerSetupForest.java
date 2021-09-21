package simcitree.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import simcitree.Main;
import simcitree.forest.Foret;

import java.io.IOException;

/**
 * Controller Graphic permettant d'initialiser les paramètres de la forêt.
 */
public class ControllerSetupForest {

    public TextField textRayonDisp;
    public TextField textRayonComp;
    public TextField textTauxReprod;
    public TextField textTauxMort;
    public TextField textNbArbre;
    public TextField textTauxComp;

    /**
     * Crée une forêt avec les paramètres mis par l'utilisateur.
     * Les données sont vérifiées avant d'être utilisées.
     * Fait un retour à l'utilisateur s'il a mis une mauvaise donnée.
     * @throws IOException
     */
    public void createForest() throws IOException {

        String rayonDisp = textRayonDisp.getText();
        String rayonComp = textRayonComp.getText();
        String tauxReprod = textTauxReprod.getText();
        String tauxMort = textTauxMort.getText();
        String tauxComp = textTauxComp.getText();
        String nbArbre = textNbArbre.getText();


        if (rayonDisp.equals("") || rayonComp.equals("") || tauxReprod.equals("") || tauxMort.equals("") || tauxComp.equals("") || nbArbre.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veillez remplir tous les champs !", ButtonType.OK);
            alert.showAndWait();
        } else if ( ( !rayonDisp.matches("[0-9]*[.][0-9]*") && !rayonDisp.matches("[0-9]*") )
                ||  ( !rayonComp.matches("[0]*[.][0-9]*")  && !rayonComp.matches("[0-9]*") )
                ||  ( !tauxReprod.matches("[0-9]*[.][0-9]*") && !tauxReprod.matches("[0-9]*") )
                ||  ( !tauxMort.matches("[0-9]*[.][0-9]*") && !tauxMort.matches("[0-9]*") )
                ||  ( !tauxComp.matches("[0-9]*[.][0-9]*") && !tauxComp.matches("[0-9]*") ) ){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veillez entrer uniquement des nombres ou des nombres à virgule (avec un point) pour :\n" +
                    " - Rayon de Dispersion.\n" +
                    " - Rayon de Compétition.\n" +
                    " - Taux de Reproduction.\n" +
                    " - Taux de Mortalité.\n" +
                    " - Taux de Compétition.\n",
                    ButtonType.OK);
            alert.showAndWait();
        } else if (!nbArbre.matches("[0-9]*")){ //Si c'est pas des chiffres
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veillez entrer uniquement des chiffres !", ButtonType.OK);
            alert.showAndWait();
        } else {
            Main.foret = new Foret(Double.parseDouble(rayonDisp), Double.parseDouble(rayonComp), Double.parseDouble(tauxReprod), Double.parseDouble(tauxMort), Double.parseDouble(tauxComp) ,Integer.parseInt(nbArbre));
            Main.stage.close();
            Main.changeScene("/layout/forest.fxml", true);
        }
    }

}
