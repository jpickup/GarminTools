package com.johnpickup.app.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ManFormController {
    @FXML
    protected void onRunButtonClick() {
        outputText.setText("Run clicked");
    }

    @FXML
    TextArea outputText;
}
