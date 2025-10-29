package com.mycompany.api.github.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Versión única de MainApp que lanza directamente la interfaz JavaFX.
 * Ya no usa modo consola.
 */
public class MainApp extends Application {

    @Override
 
public void start(Stage stage) throws Exception {
    System.out.println("Ruta FXML: " + getClass().getResource("/ui/Main.fxml"));
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Main.fxml"));
    Scene scene = new Scene(loader.load());
    stage.setTitle("GitHub Client — Proyecto");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
}


    public static void main(String[] args) {
        launch(args); // Lanza la app JavaFX
    }
}
