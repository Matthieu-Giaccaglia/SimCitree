package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTree implements Initializable {
    public ListView listView;
    public Button buttonArbre;
    private Foret foret;

    public void addArbre(ActionEvent actionEvent) {
        listView.getItems().add(listView.getItems().size(), "Arbre");

    }

    public void setForet (Foret foret) {
        this.foret = foret;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setCellFactory(param -> new XCell());
        listView.getItems().add(listView.getItems().size(), "Arbre");
    }

    static class XCell extends ListCell<String> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button = new Button("dej");
        String lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(actionEvent -> getListView().getItems().remove(getItem()));
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
