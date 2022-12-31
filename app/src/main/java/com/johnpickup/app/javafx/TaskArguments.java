package com.johnpickup.app.javafx;

import java.io.File;
import java.util.Map;

public class TaskArguments {
    private final File inputFile;
    private final File outputDir;
    private final Map<String, Object> options;

    TaskArguments(File inputFile, File outputDir, Map<String, Object> options) {
        this.inputFile = inputFile;
        this.outputDir = outputDir;
        this.options = options;
    }

    public static TaskArgumentsBuilder builder() {
        return new TaskArgumentsBuilder();
    }

    public File getInputFile() {
        return this.inputFile;
    }

    public File getOutputDir() {
        return this.outputDir;
    }

    public Map<String, Object> getOptions() {
        return this.options;
    }

    public String toString() {
        return "TaskArguments(inputFile=" + this.getInputFile() + ", outputDir=" + this.getOutputDir() + ", options=" + this.getOptions() + ")";
    }

    public static class TaskArgumentsBuilder {
        private File inputFile;
        private File outputDir;
        private Map<String, Object> options;

        TaskArgumentsBuilder() {
        }

        public TaskArgumentsBuilder inputFile(File inputFile) {
            this.inputFile = inputFile;
            return this;
        }

        public TaskArgumentsBuilder outputDir(File outputDir) {
            this.outputDir = outputDir;
            return this;
        }

        public TaskArgumentsBuilder options(Map<String, Object> options) {
            this.options = options;
            return this;
        }

        public TaskArguments build() {
            return new TaskArguments(inputFile, outputDir, options);
        }

        public String toString() {
            return "TaskArguments.TaskArgumentsBuilder(inputFile=" + this.inputFile + ", outputDir=" + this.outputDir + ", options=" + this.options + ")";
        }
    }
}
