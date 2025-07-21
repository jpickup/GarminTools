package com.johnpickup.app.javafx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLauncher {
    private static final Logger log = LoggerFactory.getLogger(AppLauncher.class);

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> log.error("Unhandled error", e));
        MainForm.main(args);
    }
}
