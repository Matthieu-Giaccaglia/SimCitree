package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sample.jfxutils.chart.ChartPanManager;
import sample.jfxutils.chart.FixedFormatTickFormatter;
import sample.jfxutils.chart.JFXChartUtil;
import sample.jfxutils.chart.StableTicksAxis;

import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class ControllerForest implements Initializable {

    public ToolBar toolbar;
    public VBox vbox;
    public Button buttonStart;
    public Button buttonPause;
    public Label labelNbTour;
    private int nbTourEcoule = 0;
    private AnimationTimer animationTimer;
    private MediaPlayer mediaPlayer;

    private long startTime;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vbox.getChildren().add(Main.sc);
        startTime = System.currentTimeMillis();

        //Set chart to format dates on the X axis
        SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );
        format.setTimeZone( TimeZone.getTimeZone( "GMT" ) );


        Main.serie = new XYChart.Series<Number, Number>();
        Main.serie.setName( "Data" );

        Main.sc.getData().add( Main.serie );


        //addDataTimeline.setCycleCount( Animation.INDEFINITE );

        Main.sc.setOnMouseMoved( new EventHandler<MouseEvent>() {
            @Override
            public void handle( MouseEvent mouseEvent ) {
                double xStart = Main.sc.getXAxis().getLocalToParentTransform().getTx();
                double axisXRelativeMousePosition = mouseEvent.getX() - xStart;

            }
        } );

        //Panning works via either secondary (right) mouse or primary with ctrl held down
        ChartPanManager panner = new ChartPanManager( Main.sc );
        panner.setMouseFilter( new EventHandler<MouseEvent>() {
            @Override
            public void handle( MouseEvent mouseEvent ) {
                if ( mouseEvent.getButton() == MouseButton.SECONDARY ||
                        ( mouseEvent.getButton() == MouseButton.PRIMARY &&
                                mouseEvent.isShortcutDown() ) ) {
                    //let it through
                } else {
                    mouseEvent.consume();
                }
            }
        } );
        panner.start();

        //Zooming works only via primary mouse button without ctrl held down
        JFXChartUtil.setupZooming( Main.sc, new EventHandler<MouseEvent>() {
            @Override
            public void handle( MouseEvent mouseEvent ) {
                if ( mouseEvent.getButton() != MouseButton.PRIMARY ||
                        mouseEvent.isShortcutDown() )
                    mouseEvent.consume();
            }
        } );

        JFXChartUtil.addDoublePrimaryClickAutoRangeHandler( Main.sc );



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