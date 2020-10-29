package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerConfig {
    public Button buttonStart;
    public TextField textFieldTour;
    public TextField textFieldTime;
    private Foret foret;

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
            foret = new Foret(Integer.parseInt(cardno), Integer.parseInt(cardno2));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/layout/forest.fxml"));

            ControllerForest controllerForest = new ControllerForest();
            controllerForest.setForet(foret);

            loader.setController(controllerForest);

            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(loader.load()));
        }

    }

    public Foret getForet(){
        return foret;
    }
}
