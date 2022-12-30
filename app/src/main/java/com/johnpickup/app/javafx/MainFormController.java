package com.johnpickup.app.javafx;

import ch.qos.logback.classic.Logger;
import com.johnpickup.app.task.UiTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collections;

public class MainFormController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MainFormController.class);

    UiAppender uiAppender;
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

    private final FileChooser fileChooser = new FileChooser();
    private final DirectoryChooser directoryChooser = new DirectoryChooser();


    public void inputFileChooserClick(ActionEvent actionEvent) {
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
        ConversionType selected = (ConversionType)conversionCombo.getValue();
        try {
            if (selected.getTask() != null) {
                TaskArguments args = TaskArguments.builder()
                        .inputFile(new File(inputFileName.getText()))
                        .outputDir(new File(outputDirName.getText()))
                        .options(Collections.singletonMap("reverse", reverseRouteCheckbox.isSelected()))
                        .build();
                UiTask task = (UiTask) selected.getTask().getDeclaredConstructor(TaskArguments.class).newInstance(args);
                task.messageProperty().addListener((w, o, n) -> log.info(n));
                new Thread(task).start();
            }
        } catch (Exception e) {
            log.error("Unexpected error", e);
        }
    }

    public void onConversionComboAction(ActionEvent actionEvent) {
        ConversionType selected = (ConversionType)conversionCombo.getSelectionModel().getSelectedItem();
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(selected.getFilePattern());
        reverseRouteCheckbox.setDisable(selected != ConversionType.GPX_TO_FIT);
    }

    public void init() {
        outputDirName.setText(System.getProperty("user.home"));
        conversionCombo.getItems().addAll(ConversionType.GPX_TO_FIT, ConversionType.SCHEDULE_TO_FIT, ConversionType.SCHEDULE_TO_ICAL);
        uiAppender = new UiAppender((s) ->
                Platform.runLater(() -> outputText.appendText(s + "\n")));
        Logger root = (Logger) (LoggerFactory.getLogger("root"));
        root.addAppender(uiAppender);
        uiAppender.start();

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            log.error(throwable.getMessage());
        });
    }
}
