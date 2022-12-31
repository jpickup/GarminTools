package com.johnpickup.app.converter;

import com.johnpickup.garmin.parser.MaximumPace;
import com.johnpickup.garmin.parser.MinimumPace;
import com.johnpickup.garmin.parser.Pace;
import com.johnpickup.garmin.parser.PaceRange;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that returns the correct converter instance based on the type of pace object passed in
 */
public class PaceConverterFactory {
    private static PaceConverterFactory instance;
    private Map<Class, PaceConverter> converters = new HashMap<>();

    private PaceConverterFactory() {
        register(new PaceRangeConverter(), PaceRange.class);
        register(new MaximumPaceConverter(), MaximumPace.class);
        register(new MinimumPaceConverter(), MinimumPace.class);
    }

    public void register(PaceConverter converter, Class aClass) {
        converters.put(aClass, converter);
    }

    public static PaceConverterFactory getInstance() {
        if (instance == null) {
            instance = new PaceConverterFactory();
        }
        return instance;
    }

    public PaceConverter getPaceConverter(Pace pace) {
        return converters.get(pace.getClass());
    }
}
