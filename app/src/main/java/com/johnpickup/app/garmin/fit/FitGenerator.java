package com.johnpickup.app.garmin.fit;

import com.garmin.fit.Mesg;

import java.util.List;

/**
 * Created by john on 31/12/2016.
 */
public interface FitGenerator {
    int PRODUCT_ID = 65534; //484;
    long SERIAL_NO = 1L;
    int SOFTWARE_VERSION = 16; //16023;
    short HARDWARE_VERSION = 0;

    List<Mesg> generate();
}
