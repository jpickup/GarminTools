package com.johnpickup.gui;

import com.johnpickup.GarminScheduleGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.io.File;

/**
 * Swing worker - allows the UI to update during generation
 */
@RequiredArgsConstructor
@Slf4j
class ScheduleConversionWorker extends SwingWorker<Object, Object> {
    private final GarminScheduleGenerator generator;
    private final File inputFile;
    private final File outputDir;

    @Override
    protected Object doInBackground() throws Exception {
        generator.generate(inputFile, outputDir);
        log.info("Done!");
        return null;
    }
}
