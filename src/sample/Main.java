package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Foret foret;
    public static Stage stage;

    public static XYChart.Series<Number, Number>  serie = new XYChart.Series<Number, Number>();
    public static ScatterChart<Number, Number> sc = new ScatterChart<>(new NumberAxis(0,1,1), new NumberAxis(0,1,1));

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*final NumberAxis xAxis = new NumberAxis(0,1,1);
        final NumberAxis yAxis = new NumberAxis(0,1,1);
        ssc = new ScrollableScatterChart<>(xAxis, yAxis);
        stage = primaryStage;
        changeScene("layout/setup_forest.fxml", false);*/
        foret = new Foret(100,1);


        FXMLLoader loader = new FXMLLoader( getClass().getResource( "layout/forest.fxml" ) );
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScene(String FXLM, boolean resizible) throws IOException {
        stage.setResizable(resizible);
        Parent root = FXMLLoader.load(Main.class.getResource(FXLM));
        stage.setTitle("SimCitree");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
