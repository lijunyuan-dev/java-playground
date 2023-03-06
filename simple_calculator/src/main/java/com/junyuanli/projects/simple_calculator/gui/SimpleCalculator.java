package com.junyuanli.projects.simple_calculator.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SimpleCalculator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleCalculator.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
//        System.out.println(javafx.scene.text.Font.getFamilies());
    }

    public static void main(String[] args) {
        launch();
    }
}