package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxutils.JFXUtil;

import java.io.IOException;

public class Main extends Application {

    public static Foret foret;
    public static Stage stage = new Stage();

    public static XYChart.Series<Number, Number>  serie = new XYChart.Series<Number, Number>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        foret = new Foret(100,1);

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "layout/setup_forest.fxml" ) );
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScene(String FXLM, boolean resizible, boolean FXMLForest) throws IOException {
        stage.setResizable(resizible);
        stage.setTitle("SimCitree");

        if (FXMLForest) {
            Region region = FXMLLoader.load(Main.class.getResource(FXLM));
            StackPane root = JFXUtil.createScalePane( region, 500, 500, true );
            stage.setScene(new Scene(root));
        } else {
            Parent root = FXMLLoader.load(Main.class.getResource(FXLM));
            stage.setScene(new Scene(root));
        }

        stage.show();
    }

}
