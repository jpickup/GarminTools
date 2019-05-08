package com.johnpickup.parser;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by john on 03/01/2017.
 */
@Slf4j
public class WorkoutTextParser {
    public Workout parse(String workoutInput) throws IOException {
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
                    (errorHandler.getErrorMessage()==null?
                            "":
                            (" - " + errorHandler.getErrorMessage()))
            );
        }
        else {
            return result;
        }

    }

}
