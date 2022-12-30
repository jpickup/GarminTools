package com.johnpickup.app.javafx;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.util.Map;

@Builder
@ToString
@Getter
public class TaskArguments {
    private final File inputFile;
    private final File outputDir;
    private final Map<String, Object> options;
}
