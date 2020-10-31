package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForest implements Initializable {

    @FXML
    public StackPane stackPane;
    private GridPane gridPane = new GridPane();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Main.stage.setMaximized(true);

        for(int i = 1; i <= Main.foret.getTaille(); i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            gridPane.getColumnConstraints().add(column);
        }

        for(int i = 1; i <= Main.foret.getTaille(); i++) {
            RowConstraints row = new RowConstraints(40);
            gridPane.getRowConstraints().add(row);
        }

        gridPane.setGridLinesVisible(true);

        for (Arbre a : Main.foret.getList()) {
            gridPane.add(new ImageView(new Image(getClass().getResource("raw/arbre.png").toExternalForm() ,40,40,false,false)), a.getX(), a.getY());
        }

        ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(gridPane);

        final double SCALE_DELTA = 1.1;
        gridPane.setOnScroll(event -> {
            event.consume();

            if (event.getDeltaY() == 0) {
                return;
            }

            double scaleFactor =
                    (event.getDeltaY() > 0)
                            ? SCALE_DELTA
                            : 1/SCALE_DELTA;

            gridPane.setScaleX(gridPane.getScaleX() * scaleFactor);
            gridPane.setScaleY(gridPane.getScaleY() * scaleFactor);
        });

        stackPane.getChildren().add(zoomableScrollPane);
    }
}
