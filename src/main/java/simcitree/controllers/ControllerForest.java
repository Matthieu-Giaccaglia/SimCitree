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
            private double event = Main.foret.getDureeNextEvent();
            private boolean finish = true;

            @Override
            public void handle(long now) {

                if ((now - lastEvenement)/ 1_000_000_000.0 >= event && Main.foret.getList().size() != 0){
                    System.out.println("-------------------------------------------");
                    nbTourEcoule++;

                    Main.foret.applyEvent();
                    labelNbTour.setText(String.valueOf(nbTourEcoule));
                    labelNbArbres.setText(String.valueOf(Main.foret.getList().size()));
                    ecrireFichier();

                    lastEvenement = now;
                    event = Main.foret.getDureeNextEvent();
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
    }

    public void ecrireFichier(){
        try {
            String data = labelNbArbres.getText();
            String data2 = labelNbTour.getText();

            FileWriter fWriter = new FileWriter("donnees.txt");
            fWriter.write("Nombres d'arbres :");
            fWriter.write(data);
            fWriter.write(" Nombres d'événements :");
            fWriter.write(data2);
            fWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}