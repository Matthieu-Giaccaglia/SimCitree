package sample;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ControllerForest implements Initializable {

    private static ScrollableGrid gridPane = new ScrollableGrid();
    public ToolBar toolbar;
    public VBox vbox;
    public Button buttonStart;
    public Button buttonPause;
    public Label labelNbTour;
    private int nbTourEcoule = 0;
    private AnimationTimer animationTimer;
    private MediaPlayer mediaPlayer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for (int i = 1; i <= Main.foret.getTaille(); i++) {
            Main.gridPane.getColumnConstraints().add(new ColumnConstraints(40));
            Main.gridPane.getRowConstraints().add(new RowConstraints(40));
        }

        Main.gridPane.setGridLinesVisible(true);
        vbox.getChildren().add(Main.gridPane);

        ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(Main.gridPane);
        zoomableScrollPane.prefWidthProperty().bind(Main.stage.widthProperty().multiply(1));
        zoomableScrollPane.prefHeightProperty().bind(Main.stage.heightProperty().multiply(1));
        vbox.getChildren().add(zoomableScrollPane);

        animationTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if ((now - lastUpdate)/ 1_000_000_000.0 >= 1 && Main.foret.getNbTour() != 0) { // delay de 1s
                    Main.foret.addFils(Main.foret.getList().get(new Random().nextInt(Main.foret.getList().size())));
                    nbTourEcoule ++;
                    labelNbTour.setText(String.valueOf(nbTourEcoule));
                    Main.foret.setNbTour(Main.foret.getNbTour() - 1);
                    lastUpdate = now;
                } else if (Main.foret.getNbTour() == 0) {
                    stop();
                }
            }
        };
        mediaPlayer = new MediaPlayer(new Media(Paths.get("src/sample/raw/test.mp3").toUri().toString()));
        mediaPlayer.setVolume(0);

    }

    public void startSimulation() {
        animationTimer.start();
        mediaPlayer.play();
    }

    public void pauseSimulation() {

        animationTimer.stop();
        mediaPlayer.pause();
    }

}