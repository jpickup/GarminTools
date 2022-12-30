package com.johnpickup.app.garmin.fit;

import com.garmin.fit.Decode;
import com.garmin.fit.Mesg;
import com.garmin.fit.MesgBroadcaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FitReader {
    public static List<Mesg> read(File input) throws IOException {
        List<Mesg> result = new ArrayList<>();

        FileInputStream in = null;
        try {
            in = new FileInputStream(input);
            Decode decode = new Decode();
            MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);

            decode.read(in, mesg -> result.add(mesg));
        }
        finally {
            if (in != null) {
                in.close();
            }
        }
        return result;
    }
}
