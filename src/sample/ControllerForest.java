package sample;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ControllerForest implements Initializable {

    private static ScrollableGrid gridPane = new ScrollableGrid();
    public ToolBar toolbar;
    public VBox vbox;
    private int saveNbTour;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Main.stage.setMaximized(true);


        for (int i = 1; i <= Main.foret.getTaille(); i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            Main.gridPane.getColumnConstraints().add(column);
        }

        for (int i = 1; i <= Main.foret.getTaille(); i++) {
            RowConstraints row = new RowConstraints(40);
            Main.gridPane.getRowConstraints().add(row);
        }

        Main.gridPane.setGridLinesVisible(true);


        vbox.getChildren().add(Main.gridPane);

        Button buttonStart = new Button("Start");
        buttonStart.setOnAction(actionEvent -> startSimulation());

        Button buttonPause = new Button("Pause");
        buttonPause.setOnAction(actionEvent -> pauseSimulation());

        toolbar.getItems().add(buttonStart);
        toolbar.getItems().add(new Separator());
        toolbar.getItems().add(buttonPause);


        ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(Main.gridPane);

        zoomableScrollPane.prefWidthProperty().bind(Main.stage.widthProperty().multiply(1));
        zoomableScrollPane.prefHeightProperty().bind(Main.stage.heightProperty().multiply(1));

        vbox.getChildren().add(zoomableScrollPane);
        vbox.getChildren().add(new ToolBar());


    }

    public void startSimulation() {

        //Main.foret.setNbTour(saveNbTour);
        simulation();
    }

    public void pauseSimulation() {
        //saveNbTour = Main.foret.getNbTour();
        //Main.foret.setNbTour(0);
    }

    public void simulation() {

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1000_000_000 && Main.foret.getNbTour() != 0) { // delay de 1000 ms
                    int i = new Random().nextInt(Main.foret.getList().size());
                    Main.foret.addFils(Main.foret.getList().get(i));
                    Main.gridPane.add(new ImageView(new Image(getClass().getResource("raw/arbre.png").toExternalForm(), 40, 40, false, false)), 0, 0);
                    Main.foret.setNbTour(Main.foret.getNbTour() - 1);
                    lastUpdate = now;
                } else if (Main.foret.getNbTour() == 0) {
                    stop();
                }
            }
        };

        timer.start();

    }
}