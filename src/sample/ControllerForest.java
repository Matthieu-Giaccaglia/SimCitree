package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForest implements Initializable {

    private final ScrollableGrid gridPane = new ScrollableGrid();
    public ToolBar toolbar;
    public VBox vbox;
    private int saveNbTour;


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

        vbox.getChildren().add(gridPane);

        Button buttonStart = new Button("Start");
        buttonStart.setOnAction(actionEvent -> startSimulation());

        Button buttonPause = new Button("Pause");
        buttonPause.setOnAction(actionEvent -> pauseSimulation());

        toolbar.getItems().add(buttonStart);
        toolbar.getItems().add(new Separator());
        toolbar.getItems().add(buttonPause);


        ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(gridPane);

        zoomableScrollPane.prefWidthProperty().bind(Main.stage.widthProperty().multiply(1));
        zoomableScrollPane.prefHeightProperty().bind(Main.stage.heightProperty().multiply(1));

        vbox.getChildren().add(zoomableScrollPane);
        vbox.getChildren().add(new ToolBar());


    }

    public void startSimulation() {

        Main.foret.setNbTour(saveNbTour);
        simulation();
    }

    public void pauseSimulation() {
        saveNbTour = Main.foret.getNbTour();
        Main.foret.setNbTour(0);
    }

    public void simulation(){
        while (Main.foret.getNbTour() != 0) {
            Main.foret.setNbTour(Main.foret.getNbTour()-1);
            //evenement
            //wait
        }
    }



    public static class ScrollableGrid extends GridPane {

        final double SCALE_DELTA = 1.1;

        public ScrollableGrid() {

            setOnScroll(event -> {
                event.consume();

                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor =
                        (event.getDeltaY() > 0)
                                ? SCALE_DELTA
                                : 1/SCALE_DELTA;

                this.setScaleX(this.getScaleX() * scaleFactor);
                this.setScaleY(this.getScaleY() * scaleFactor);
            });
        }
    }
}
