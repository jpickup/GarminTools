package com.johnpickup;

import com.johnpickup.garmin.converter.CourseConverter;
import com.johnpickup.garmin.fit.FitSaver;
import com.johnpickup.garmin.route.Course;
import com.johnpickup.gpx.GpxReader;
import com.johnpickup.gpx.GpxType;
import com.johnpickup.gui.RouteConverterForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

@Slf4j
public class GarminRouteGenerator {
    private GpxReader gpxReader = new GpxReader();
    private CourseConverter courseConverter = new CourseConverter();

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
        RouteConverterForm.display();
    }

    private void convert(String inputFilename, boolean reverse) throws JAXBException, FileNotFoundException {
        String outputDir = FilenameUtils.getPath(inputFilename);
        convert(new File(inputFilename), new File(outputDir), reverse);
    }

    public void convert(File inputFile, File outputFile, boolean reverse) throws JAXBException, FileNotFoundException {
        if (outputFile.isDirectory()) {
            outputFile = new File(outputFile, FilenameUtils.getBaseName(inputFile.getName()) + ".fit");
        }

        log.info("Converting {} to {}", inputFile, outputFile);
        GpxType gpxType = gpxReader.readGpxFile(inputFile);
        Course convertedCourse = courseConverter.convert(gpxType);
        log.info("Converted GPX '{}' to a course containing {} points", convertedCourse.getName(), convertedCourse.size());
        if (reverse) {
            log.info("Reversing route");
            convertedCourse.reverse();
        }
        log.info("Saving route to {}", outputFile);
        FitSaver.save(convertedCourse, outputFile);
        log.info("Converted {} to {}", inputFile, outputFile);
    }
}
