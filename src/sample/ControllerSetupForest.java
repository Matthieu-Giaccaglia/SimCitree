package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerSetupForest {
    public Button buttonStart;
    public TextField textFieldTour;
    public TextField textFieldTime;

    public void start(ActionEvent actionEvent) throws IOException {
        String cardno = textFieldTour.getText();
        String cardno2 = textFieldTime.getText();


        if (cardno.equals("") || cardno2.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veillez entrer des chiffres !", ButtonType.OK);
            alert.showAndWait();
        }else if (!cardno.matches("[0-9]*") || !cardno2.matches("[0-9]*")){ //Si c'est pas des chiffres
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veillez entrer uniquement des chiffres !", ButtonType.OK);
            alert.showAndWait();
        }else{

            Main.foret = new Foret(Integer.parseInt(cardno), Integer.parseInt(cardno2));

            Main.stage.close();
            Main.changeScene("layout/tree_config.fxml", true);

        }

    }

}
