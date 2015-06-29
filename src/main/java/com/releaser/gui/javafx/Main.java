package com.releaser.gui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        String mainView = "view/main.fxml";
        URL resourceUrl = getClass().getClassLoader().getResource(mainView);

        if (resourceUrl == null) {
            throw new Exception("Could not find '" + mainView + "' in resources");
        }

        Parent root = FXMLLoader.load(resourceUrl);
        primaryStage.setTitle("Releaser");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
