package simcitree;

import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jfxutils.chart.ChartPanManager;
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
    private Chrono chrono;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.chrono = new Chrono();

        chart.getData().add(Main.serie);

        JFXChartUtil.setupZooming( chart, mouseEvent -> {
            if ( mouseEvent.getButton() != MouseButton.PRIMARY ||
                    mouseEvent.isShortcutDown() )
                mouseEvent.consume();
        });
        ChartPanManager panner = new ChartPanManager( chart );
        panner.start();
        JFXChartUtil.addDoublePrimaryClickAutoRangeHandler(chart);


        animationTimer = new AnimationTimer() {
            private long lastEvenement = 0;
            private long lastSecond = 0;

            @Override
            public void handle(long now) {
                //System.out.println("-------------------------------------------");
                double nowTest = (now - lastEvenement)/ 1_000_000_000.0;
                double event = Main.foret.getDureeNextEven();
                //System.out.println(nowTest);
                if (nowTest >= event && Main.foret.getList().size() != 0){
                    System.out.println("-------------------------------------------");
                    System.out.println(nowTest >= event);
                    System.out.println(nowTest);
                    System.out.println("next event "+  event);
                    nbTourEcoule++;
                    Main.foret.applyEvent(nbTourEcoule);
                    labelNbTour.setText(String.valueOf(nbTourEcoule));
                    labelNbArbres.setText(String.valueOf(Main.foret.getList().size()));
                    lastEvenement = now;
                    System.out.println("-------------------------------------------");
                }
                if (Main.foret.getList().size() == 0) {
                    stop();
                    chrono.stop();
                }
                if ((now - lastSecond)/1_000_000_000.0 >= 1) {
                    labelTime.setText(chrono.getActuelDureeTxt());
                    lastSecond = now;
                }
            }
        };


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
    }

    public void pauseSimulation() {
        this.chrono.pause();
        animationTimer.stop();
        //ok
    }

}