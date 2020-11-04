package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSetupTree implements Initializable {
    public ListView listView;
    public Button buttonArbre;
    public TextField textFieldRayonC;
    public TextField textFieldRayonD;
    public TextField textFieldChanceR;
    public TextField textFieldIntensite;
    public TextField textFieldLifeE;
    public TextField textFieldY;
    public TextField textFieldX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setCellFactory(param -> new XCell());
    }

    public void addArbre(ActionEvent actionEvent) {

        String posX = textFieldX.getText();
        String posY = textFieldY.getText();
        String chanceRep = textFieldChanceR.getText();
        String intensiteComp = textFieldIntensite.getText();
        String esperanceVie = textFieldLifeE.getText();
        String rayonComp = textFieldRayonC.getText();
        String rayonDisp = textFieldRayonD.getText();


        if (posX.equals("") || posY.equals("") || chanceRep.equals("") || intensiteComp.equals("") || esperanceVie.equals("") || rayonComp.equals("") || rayonDisp.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veillez remplir tous les champs !", ButtonType.OK);
            alert.showAndWait();
        } else if (!posX.matches("[0]*[.][0-9]*") || !posY.matches("[0]*[.][0-9]*") || !rayonComp.matches("[0-9]*[.][0-9]*") || !rayonDisp.matches("[0-9]*[.][0-9]*") || !intensiteComp.matches("[0-9]*[.][0-9]*") || !chanceRep.matches("[0-9]*[.][0-9]*") || !esperanceVie.matches("[0-9]*[.][0-9]*") ) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veillez entrer uniquement des nombres à virgule (avec un point) pour :\n" +
                    " - L'espérance de vie.\n" +
                    " - L'instensité de compétition.\n" +
                    " - La chance de reproduction." +
                    " - Position X.\n" +
                    " - Position Y.\n" +
                    " - Rayon Compétition.\n" +
                    " - Rayon Disperstion.", ButtonType.OK);
            alert.showAndWait();
        } else if (Double.parseDouble(posX) >= 1 || Double.parseDouble(posX) < 0 || Double.parseDouble(posY) >= 1 || Double.parseDouble(posY) < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Les coordonnées de l'arbre doivent être comprise entre 0 et 1 !", ButtonType.OK);
            alert.showAndWait();
        } else {
            listView.getItems().add(listView.getItems().size(), "Arbre (" + posX + " ," + posY + ")");
            Arbre arbre = new Arbre(Double.parseDouble(posX), Double.parseDouble(posY), Double.parseDouble(rayonComp), Double.parseDouble(rayonDisp), Double.parseDouble(esperanceVie), Double.parseDouble(intensiteComp), Double.parseDouble(chanceRep));
            Main.foret.addArbre(arbre);
        }

    }




    public void startSimulation(ActionEvent actionEvent) throws IOException {

        if (Main.foret.getList().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veillez ajouter au moins un arbre !", ButtonType.OK);
            alert.showAndWait();
        } else {
            Main.stage.close();
            Main.changeScene("layout/forest.fxml", true, true);
        }
    }


    static class XCell extends ListCell<String> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button = new Button("Supprimé");
        String lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(actionEvent -> {
                Main.foret.deleteArbre(getIndex());
                getListView().getItems().remove(getItem());
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(item!=null ? item : "<null>");
                setGraphic(hbox);
            }
        }
    }
}
