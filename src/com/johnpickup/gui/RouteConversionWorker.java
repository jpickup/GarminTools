package com.johnpickup.gui;

import com.johnpickup.GarminRouteGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.io.File;

/**
 * Swing worker - allows the UI to update during generation
 */
@RequiredArgsConstructor
@Slf4j
class RouteConversionWorker extends SwingWorker<Object, Object> {
    private final GarminRouteGenerator generator;
    private final File inputFile;
    private final File outputDir;

    @Override
    protected Object doInBackground() {
        try {
            generator.convert(inputFile, outputDir);
        } catch (Exception e) {
            log.error("Failed to convert {}", inputFile.getName(), e);
        }
        return null;
    }
}
