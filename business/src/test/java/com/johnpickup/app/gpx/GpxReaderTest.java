package com.johnpickup.app.gpx;

import com.johnpickup.gpx.GpxType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class GpxReaderTest {
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