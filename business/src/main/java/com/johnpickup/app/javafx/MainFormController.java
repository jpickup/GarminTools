package com.johnpickup.app.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class MainFormController {
    @FXML
    TextField inputFileName;

    @FXML
    Button inputFileChooser;

    @FXML
    TextField outputDirName;

    @FXML
    Button outputDirChooser;

    @FXML
    ComboBox conversionCombo;

    @FXML
    CheckBox reverseRouteCheckbox;
    @FXML
    TextArea outputText;

    public void inputFileChooserClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        String selection = inputFileName.getText();
        File selectedFile = new File(selection);
        if (!selectedFile.canRead()) {
            selectedFile = new File(System.getProperty("user.home"));
        }
        if (selectedFile.isDirectory()) {
            fileChooser.setInitialDirectory(selectedFile);
        }
        else {
            fileChooser.setInitialDirectory(selectedFile.getParentFile());
        }
        File newFile = fileChooser.showOpenDialog(inputFileName.getScene().getWindow());
        if (newFile != null) {
            inputFileName.setText(newFile.getPath());
        }
    }

    public void outputDirChooserClick(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        String selection = outputDirName.getText();
        File selectedDir = new File(selection);
        if (!selectedDir.canRead()) {
            selectedDir = new File(System.getProperty("user.home"));
        }
        directoryChooser.setInitialDirectory(selectedDir);
        File selectedDirectory = directoryChooser.showDialog(outputDirName.getScene().getWindow());
        if (selectedDirectory != null) {
            outputDirName.setText(selectedDirectory.getPath());
        }
    }

    public void runButtonClick(ActionEvent actionEvent) {
        outputText.setText("Run clicked");
    }

    public void init() {
        conversionCombo.getItems().addAll("One", "Two", "Three");
    }
}
