package com.johnpickup.app.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
public class MainForm extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainForm.class.getResource("/javafx/main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 440);
        stage.setTitle("Garmin Tools");
        stage.setScene(scene);
        stage.getIcons().add(
                new Image(MainForm.class.getResourceAsStream( "/GarminWorkoutCreator.png" )));
        MainFormController controller = fxmlLoader.getController();
        controller.init();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
