package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerConfig  {
    public Button buttonStart;
    public TextField textFieldTour;
    public TextField textFieldTime;
    private Foret foret;
    private ArrayList<String> list = new ArrayList<>();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/layout/tree_config.fxml"));
            Parent root = loader.load();

            ControllerTree controllerTree = loader.getController();
            controllerTree.setForet(foret);


            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(root));
        }

    }

    public Foret getForet(){
        return foret;
    }


}
