package com.johnpickup.converter;

import com.johnpickup.parser.HeartRate;
import com.johnpickup.parser.HeartRateRange;
import com.johnpickup.parser.HeartRateZone;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that returns the correct converter instance based on the type of heart rate target object passed in
 */
public class HeartRateConverterFactory {
    private static HeartRateConverterFactory instance;
    private Map<Class, HeartRateConverter> converters = new HashMap<>();

    private HeartRateConverterFactory() {
        register(new ZoneHeartRateConverter(), HeartRateZone.class);
        register(new CustomHeartRateConverter(), HeartRateRange.class);
    }

    public void register(HeartRateConverter converter, Class aClass) {
        converters.put(aClass, converter);
    }

    public static HeartRateConverterFactory getInstance() {
        if (instance == null) {
            instance = new HeartRateConverterFactory();
        }
        return instance;
    }

    public HeartRateConverter getHeartRateConverter(HeartRate heartRate) {
        return converters.get(heartRate.getClass());
    }
}
