package simcitree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Foret foret;
    public static Stage stage = new Stage();

    public static XYChart.Series<Number, Number>  serie = new XYChart.Series<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        changeScene("layout/setup_forest.fxml",false);
    }




    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScene(String FXLM, boolean resizible) throws IOException {
        stage.setResizable(resizible);
        stage.setTitle("SimCitree");
        Parent root = FXMLLoader.load(Main.class.getResource(FXLM));
        stage.setScene(new Scene(root));
        stage.show();
    }

}
