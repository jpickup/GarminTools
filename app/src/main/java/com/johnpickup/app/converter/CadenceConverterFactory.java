package com.johnpickup.app.converter;

import com.johnpickup.garmin.parser.Cadence;
import com.johnpickup.garmin.parser.CadenceRange;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that returns the correct converter instance based on the type of cadence target object passed in
 */
public class CadenceConverterFactory {
    private static CadenceConverterFactory instance;
    private final Map<Class, CadenceConverter> converters = new HashMap<>();

    private CadenceConverterFactory() {
        register(new CustomCadenceConverter(), CadenceRange.class);
    }

    public void register(CadenceConverter converter, Class aClass) {
        converters.put(aClass, converter);
    }

    public static CadenceConverterFactory getInstance() {
        if (instance == null) {
            instance = new CadenceConverterFactory();
        }
        return instance;
    }

    public CadenceConverter getCadenceConverter(Cadence cadence) {
        return converters.get(cadence.getClass());
    }
}
