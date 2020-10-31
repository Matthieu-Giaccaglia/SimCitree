package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Foret foret;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        changeScene("layout/forest_config.fxml", false);
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
