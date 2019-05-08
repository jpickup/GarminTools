package com.johnpickup.gui;

import com.johnpickup.CalendarScheduleGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

@Slf4j
public class CalendarConverterForm {
    private JPanel contentPane;
    private JButton convertButton;
    private JTextField inputFileField;
    private JButton fileChooserButton;
    private JButton directoryChooserButton;
    private JTextField outputDirField;
    private JTextArea loggingOutput;
    private JPanel inputFilePanel;
    private JPanel outputDirPanel;
    private JPanel bottomPanel;
    private JScrollPane logPane;

    private final CalendarScheduleGenerator calendarGenerator = new CalendarScheduleGenerator();

    private SwingWorker<Object, Object> worker;

    private void init() {
        SwingMessageAppender appender = new SwingMessageAppender(loggingOutput);
        LogManager.getRootLogger().addAppender(appender);

        inputFileField.setText(System.getProperty("user.dir"));
        outputDirField.setText(System.getProperty("user.dir"));

        fileChooserButton.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();
            File f = new File(inputFileField.getText());
            if (f.exists()) {
                if (f.isDirectory()) {
                    fc.setCurrentDirectory(f);
                }
                else {
                    fc.setCurrentDirectory(f.getParentFile());
                }
            }

            fc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return (f.getName().endsWith(".xls") || f.getName().endsWith(".xlsx"));
                }

                @Override
                public String getDescription() {
                    return "Excel Files (*.xls;*.xlsx)";
                }
            });

            int returnVal = fc.showOpenDialog(inputFileField);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                inputFileField.setText(file.getAbsolutePath());
            }
        });

        directoryChooserButton.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            File dir = new File(outputDirField.getText());
            if (dir.exists()) {
                fc.setCurrentDirectory(dir);
            }
            int returnVal = fc.showOpenDialog(outputDirField);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                outputDirField.setText(file.getAbsolutePath());
            }
        });

        convertButton.addActionListener(e -> {
            File inputFile = new File(inputFileField.getText());
            File outputDir = new File(outputDirField.getText());

            try {
                if (worker != null) {
                    worker.cancel(true);
                }
                    worker = new IcalConversionWorker(calendarGenerator, inputFile, outputDir);
                    worker.execute();

            } catch (Exception e1) {
                log.error(e1.getMessage());
            }
        });

    }

    public static void display() {
        JFrame frame = new JFrame("Garmin Workout Calendar Converter");
        CalendarConverterForm form = new CalendarConverterForm();
        form.init();
        frame.setContentPane(form.contentPane);

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        display();
    }
}
