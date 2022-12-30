package com.johnpickup.app.garmin;

import com.garmin.fit.FileEncoder;
import com.garmin.fit.Fit;
import com.johnpickup.app.garmin.fit.FitGenerator;

import java.io.File;

/**
 * Class that can save output from a FitGenerator to a fie
 */
public class WorkoutSaver {

    public void save(FitGenerator fitGenerator, String filename) {
        save(fitGenerator, new java.io.File(filename));
    }

    public void save(FitGenerator fitGenerator, File file) {
        FileEncoder encode = new FileEncoder( file, Fit.ProtocolVersion.V1_0);
        encode.write(fitGenerator.generate());
        encode.close();
    }
}
