package com.geekbrains.calculator;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.awt.*;

public class CalculatorController {
    @FXML
    public TextField output;

    public void inputDigit(ActionEvent actionEvent){
        Button source = (Button) actionEvent.getSource();
        String digit = source.getText();
        output.appendText(digit);
    }
}
