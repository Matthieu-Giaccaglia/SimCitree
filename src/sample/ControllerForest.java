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
    public Label labelTime;
    private int nbTourEcoule = 0;
    private AnimationTimer animationTimer;
    private MediaPlayer mediaPlayer;
    private Chrono chrono;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.chrono = new Chrono();

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
            private long lastEvenement = 0;
            private long lastSecond = 0;

            @Override
            public void handle(long now) {
                if ((now - lastEvenement)/ 1_000_000_000.0 >= Main.foret.getDureeNextEven() && Main.foret.getList().size() != 0){
                    nbTourEcoule++;
                    Main.foret.appliquerEvenement(nbTourEcoule);
                    labelNbTour.setText(String.valueOf(nbTourEcoule));
                    labelNbArbres.setText(String.valueOf(Main.foret.getList().size()));
                    lastEvenement = now;
                } else if (Main.foret.getList().size() == 0) {
                    stop();
                    chrono.stop();
                }
                if ((now - lastSecond)/1_000_000_000.0 >= 1) {
                    labelTime.setText(chrono.getActuelDureeTxt());
                    lastSecond = now;
                }
            }
        };

        mediaPlayer = new MediaPlayer(new Media(Paths.get("src/sample/raw/test.mp3").toUri().toString()));
        mediaPlayer.setVolume(0.1);
        labelNbArbres.setText(String.valueOf(Main.foret.getList().size()));
        labelNbTour.setText(String.valueOf(nbTourEcoule));
    }

    public void startSimulation() {
        if(nbTourEcoule==0) {
            this.chrono.start();
        }else {
            this.chrono.resume();
        }
        animationTimer.start();
        mediaPlayer.play();
    }

    public void pauseSimulation() {
        this.chrono.pause();
        animationTimer.stop();
        mediaPlayer.pause();
    }

}