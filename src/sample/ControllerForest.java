package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForest implements Initializable {

    @FXML
    public AnchorPane anchorPane;
    private Foret foret;


    public void setForet(Foret foretD){
        foret = foretD;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GridPane grid = new GridPane();
        grid.getStyleClass().add("game-grid");

        for(int i = 0; i < foret.getTaille(); i++) {
            ColumnConstraints column = new ColumnConstraints(20);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < foret.getTaille(); i++) {
            RowConstraints row = new RowConstraints(20);
            grid.getRowConstraints().add(row);
        }

        grid.setGridLinesVisible(true);
        anchorPane.getChildren().add(grid);
    }
}
