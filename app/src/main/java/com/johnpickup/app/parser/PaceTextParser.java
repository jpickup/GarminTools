package com.johnpickup.app.parser;

import com.johnpickup.garmin.parser.Pace;
import com.johnpickup.garmin.parser.WorkoutLexer;
import com.johnpickup.garmin.parser.WorkoutParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by john on 03/01/2017.
 */
public class PaceTextParser {
    public Pace parse(String inputText) throws IOException {
        InputStream input = new ByteArrayInputStream(inputText.getBytes());
        CharStream charStream = CharStreams.fromStream(input);
        WorkoutLexer lexer = new WorkoutLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        WorkoutParser parser = new WorkoutParser(tokens);
        return parser.pace().value;
    }

}
