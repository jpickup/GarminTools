package com.johnpickup.app.parser;

import com.johnpickup.garmin.parser.AntlrErrorHandler;
import com.johnpickup.garmin.parser.Workout;
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
public class WorkoutTextParser {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WorkoutTextParser.class);
    public Workout parse(String workoutInput) {
        try {
            InputStream input = new ByteArrayInputStream(workoutInput.getBytes());
            CharStream charStream = CharStreams.fromStream(input);
            WorkoutLexer lexer = new WorkoutLexer(charStream);
            TokenStream tokens = new CommonTokenStream(lexer);
            WorkoutParser parser = new WorkoutParser(tokens);
            AntlrErrorHandler errorHandler = new AntlrErrorHandler();
            parser.setErrorHandler(errorHandler);
            Workout result = parser.workout().w;
            if (errorHandler.isHadError()) {
                log.debug("Parser error reading {} : {}", workoutInput, errorHandler.getErrorMessage());
                throw new RuntimeException("Error parsing " + workoutInput +
                        (errorHandler.getErrorMessage() == null ?
                                "" :
                                (" - " + errorHandler.getErrorMessage()))
                );
            } else {
                return result;
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
