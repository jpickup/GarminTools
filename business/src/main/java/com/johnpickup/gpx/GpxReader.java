package com.johnpickup.gpx;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GpxReader {
    public GpxType readGpxFile(File file) throws JAXBException, FileNotFoundException {
        JAXBContext jc = JAXBContext.newInstance("com.johnpickup.gpx");
        Unmarshaller um = jc.createUnmarshaller();
        GpxType gpxType = (GpxType)JAXBIntrospector.getValue(um.unmarshal(new FileInputStream(file)));
        return gpxType;
    }
}
