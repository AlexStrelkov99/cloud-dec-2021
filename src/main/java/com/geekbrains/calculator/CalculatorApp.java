package com.geekbrains.calculator;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/geekbrains/calculator/calculator.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }
}
