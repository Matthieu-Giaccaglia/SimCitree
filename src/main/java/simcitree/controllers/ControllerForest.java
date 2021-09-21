package simcitree.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import simcitree.Main;
import simcitree.jfxutils.chart.ChartPanManager;
import simcitree.jfxutils.chart.JFXChartUtil;
import simcitree.utils.Chrono;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller graphic de la forêt.
 * Ce controller permettra de faire fonctionner la forêt.
 * Même s'il ne calcul pas le temps d'attente entre deux évènements,
 * c'est lui qui va faire le timer.
 */
public class ControllerForest implements Initializable {

    public ToolBar toolbar;
    public VBox vbox;
    public Button buttonStart;
    public Button buttonPause;
    public Label labelNbEvent;
    public Label labelNbArbres;
    public ScatterChart<Number, Number> chart;
    public Label labelTime;
    private int nbEvent = 0;
    private AnimationTimer animationTimer;
    private Chrono chrono;


    /**
     * Fonctionne appelée directement après la création de l'interface graphic.
     * @param url useless?
     * @param resourceBundle useless?
     */
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
            private long lastTimeEvenement = 0;
            private long lastSecond = 0;
            private double nextTimeEvent = Main.foret.getDureeNextEvent();

            @Override
            public void handle(long now) {

                if ((now - lastTimeEvenement)/ 1_000_000_000.0 >= nextTimeEvent && Main.foret.getList().size() != 0){
                    System.out.println("-------------------------------------------");
                    nbEvent++;

                    Main.foret.applyEvent();
                    labelNbEvent.setText(String.valueOf(nbEvent));
                    labelNbArbres.setText(String.valueOf(Main.foret.getList().size()));
                    ecrireFichier();

                    lastTimeEvenement = now;
                    nextTimeEvent = Main.foret.getDureeNextEvent();
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
        labelNbEvent.setText(String.valueOf(nbEvent));
    }

    public void startSimulation() {
        if(nbEvent ==0) {
            this.chrono.start();
        }else {
            this.chrono.resume();
        }
        animationTimer.start();
    }

    public void pauseSimulation() {
        this.chrono.pause();
        animationTimer.stop();
    }

    public void ecrireFichier(){
        try {
            FileWriter fWriter = new FileWriter("donnees.txt");
            String message = "Temps de simulation : " + labelTime.getText()
                                + " ; Nombres d'arbres : " + labelNbArbres.getText()
                                + " ; Nombre d'évènements : " + labelNbEvent.getText();
            fWriter.write(message);
            fWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}