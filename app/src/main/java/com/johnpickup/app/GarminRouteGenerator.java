package com.johnpickup.app;

import com.johnpickup.app.garmin.converter.CourseConverter;
import com.johnpickup.app.garmin.fit.FitSaver;
import com.johnpickup.app.garmin.route.Course;
import com.johnpickup.app.gpx.GpxReader;
import com.johnpickup.gpx.GpxType;
import jakarta.xml.bind.JAXBException;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class GarminRouteGenerator {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GarminRouteGenerator.class);

    private final GpxReader gpxReader = new GpxReader();
    private final CourseConverter courseConverter = new CourseConverter();

    public static void main(String[] args) {
        if (args.length == 0) {
            runGui();
        }

        try {
            GarminRouteGenerator garminRouteGenerator = new GarminRouteGenerator();
            for (String arg : args) {
                garminRouteGenerator.convert(arg, false);
            }
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }

    private static void runGui() {
        // TODO RouteConverterForm.display();
    }

    private void convert(String inputFilename, boolean reverse) throws JAXBException, FileNotFoundException {
        String outputDir = FilenameUtils.getPath(inputFilename);
        convert(new File(inputFilename), new File(outputDir), reverse);
    }

    public void convert(File inputFile, File outputFile, boolean reverse) throws JAXBException, FileNotFoundException {
        if (outputFile.isDirectory()) {
            outputFile = new File(outputFile, FilenameUtils.getBaseName(inputFile.getName()) + ".fit");
        }

        log.info("Converting {}", inputFile);
        GpxType gpxType = gpxReader.readGpxFile(inputFile);
        Course convertedCourse = courseConverter.convert(gpxType);
        log.info("Converted GPX '{}' to a course containing {} points", convertedCourse.getName(), convertedCourse.size());
        if (reverse) {
            log.info("Reversing route");
            convertedCourse.reverse();
        }
        log.info("Saving route to {}", outputFile);
        FitSaver.save(convertedCourse, outputFile);
        log.info("Saved to {}", outputFile);
    }
}
