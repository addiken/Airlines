package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("International Airlines");
        primaryStage.setScene(new Scene(root, 1000, 495));
        primaryStage.show();
        new JMetro().setParent(root);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
