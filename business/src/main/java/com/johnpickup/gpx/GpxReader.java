package com.johnpickup.gpx;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.JAXBIntrospector;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GpxReader {
    public GpxType readGpxFile(File file) throws JAXBException, FileNotFoundException {
        JAXBContext jc = JAXBContext.newInstance("com.johnpickup.gpx");
        Unmarshaller um = jc.createUnmarshaller();
        GpxType gpxType = (GpxType) JAXBIntrospector.getValue(um.unmarshal(new FileInputStream(file)));
        return gpxType;
    }
}
