package com.johnpickup.app.converter;

import com.johnpickup.garmin.parser.Power;
import com.johnpickup.garmin.parser.PowerRange;
import com.johnpickup.garmin.parser.PowerZone;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that returns the correct converter instance based on the type of heart rate target object passed in
 */
public class PowerConverterFactory {
    private static PowerConverterFactory instance;
    private final Map<Class, PowerConverter> converters = new HashMap<>();

    private PowerConverterFactory() {
        register(new ZonePowerConverter(), PowerZone.class);
        register(new CustomPowerConverter(), PowerRange.class);
    }

    public void register(PowerConverter converter, Class aClass) {
        converters.put(aClass, converter);
    }

    public static PowerConverterFactory getInstance() {
        if (instance == null) {
            instance = new PowerConverterFactory();
        }
        return instance;
    }

    public PowerConverter getPowerConverter(Power power) {
        return converters.get(power.getClass());
    }
}
