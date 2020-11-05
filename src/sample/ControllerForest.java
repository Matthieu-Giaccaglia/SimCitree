package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jfxutils.chart.JFXChartUtil;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ControllerForest implements Initializable {

    public ToolBar toolbar;
    public VBox vbox;
    public Button buttonStart;
    public Button buttonPause;
    public Label labelNbTour;
    public Label labelNbArbres;
    public ScatterChart<Number, Number> chart;
    public Label textTime;
    private int nbTourEcoule = 0;
    private AnimationTimer animationTimer;
    private MediaPlayer mediaPlayer;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chart.getData().add(Main.serie);

        JFXChartUtil.setupZooming( chart, new EventHandler<MouseEvent>() {
            @Override
            public void handle( MouseEvent mouseEvent ) {
                if ( mouseEvent.getButton() != MouseButton.PRIMARY ||
                        mouseEvent.isShortcutDown() )
                    mouseEvent.consume();
            }
        } );
        JFXChartUtil.addDoublePrimaryClickAutoRangeHandler(chart);

        NumberAxis numberXAxis = (NumberAxis) chart.getXAxis();
        numberXAxis.setLowerBound(0);
        numberXAxis.setUpperBound(1);

        NumberAxis numberYAxis = (NumberAxis) chart.getYAxis();
        numberYAxis.setLowerBound(0);
        numberYAxis.setUpperBound(1);

        animationTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if ((now - lastUpdate)/ 1_000_000_000.0 >= Main.foret.getTauxGlobal() && Main.foret.getList().size() != 0){
                    nbTourEcoule++;
                    Main.foret.appliquerEvenement(nbTourEcoule);
                    labelNbTour.setText(String.valueOf(nbTourEcoule));
                    labelNbArbres.setText(String.valueOf(Main.foret.getList().size()));
                    lastUpdate = now;
                } else if (Main.foret.getList().size() == 0) {
                    stop();
                }
            }
        };

        mediaPlayer = new MediaPlayer(new Media(Paths.get("src/sample/raw/test.mp3").toUri().toString()));
        mediaPlayer.setVolume(0.1);
        labelNbArbres.setText(String.valueOf(Main.foret.getList().size()));
        labelNbTour.setText(String.valueOf(nbTourEcoule));
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