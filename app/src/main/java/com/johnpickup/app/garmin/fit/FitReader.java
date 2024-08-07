package com.johnpickup.app.garmin.fit;

import com.garmin.fit.Decode;
import com.garmin.fit.Field;
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

            decode.read(in, result::add);
        }
        finally {
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
            try {
                List<Mesg> mesgs = read(new File(arg));
                for (Mesg mesg : mesgs) {
                    System.out.println("MESSAGE");
                    for (Field field : mesg.getFields()) {
                        System.out.println(field.getName());
                        System.out.println(field.getLongValue());
                    }

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
