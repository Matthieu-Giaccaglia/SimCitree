package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerForest implements Initializable {

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

        vbox.getChildren().add(Main.sc);
        Main.serie.setName( "Test" );
        Main.sc.getData().add( Main.serie );






        /*ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(Main.ssc);
        zoomableScrollPane.prefWidthProperty().bind(Main.stage.widthProperty().multiply(1));
        zoomableScrollPane.prefHeightProperty().bind(Main.stage.heightProperty().multiply(1));*/



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
        mediaPlayer.setVolume(0.1);

        Main.foret.addArbre(
                new Arbre(
                        0.5,
                        0.5,
                        0.1,
                        0.1,
                        0.1,
                        0.1,
                        0.1
                )
        );

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