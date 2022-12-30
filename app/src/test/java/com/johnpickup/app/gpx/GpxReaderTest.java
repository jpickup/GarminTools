package com.johnpickup.app.gpx;

import com.johnpickup.gpx.GpxType;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class GpxReaderTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GpxReaderTest.class);
    private GpxReader gpxReader;

    @Before
    public void setUp() {
        gpxReader = new GpxReader();
    }

    @Test
    public void readGpxFile() throws Exception {
        GpxType gpxType = gpxReader.readGpxFile(new File("src/test/resources/com.johnpickup.gpx/BedgeburyBlue.gpx"));
        assertNotNull(gpxType);
        log.info("{}", gpxType.getRte());
    }
}