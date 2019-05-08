package com.johnpickup.gpx;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

@Slf4j
public class GpxReaderTest {
    private GpxReader gpxReader;

    @Before
    public void setUp() {
        gpxReader = new GpxReader();
    }

    @Test
    public void readGpxFile() throws Exception {
        GpxType gpxType = gpxReader.readGpxFile(new File("test-resources/com.johnpickup.gpx/BedgeburyBlue.gpx"));
        assertNotNull(gpxType);
        log.info("{}", gpxType.getRte());
    }
}