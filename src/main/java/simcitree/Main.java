package simcitree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import simcitree.forest.Foret;

import java.io.IOException;

public class Main extends Application {

    public static Foret foret;
    public static Stage stage;

    public static XYChart.Series<Number, Number>  serie;

    @Override
    public void start(Stage primaryStage) throws Exception{
        serie = new XYChart.Series<>();
        stage = primaryStage;
        stage.setTitle("SimCitree");
        stage.getIcons().add(new Image(Main.class.getResource("/raw/logo_simcitree2.png").toExternalForm()));

        changeScene("/layout/setup_forest.fxml",false);

    }




    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScene(String FXLM, boolean resizible) throws IOException {
        stage.setResizable(resizible);
        Parent root = FXMLLoader.load(Main.class.getResource(FXLM));
        stage.setScene(new Scene(root));
        stage.show();
    }

}
