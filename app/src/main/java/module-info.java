module com.johnpickup.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;
    requires antlr4.runtime;
    requires fit;
    requires org.apache.poi.poi;
    requires biweekly;
    requires jakarta.xml.bind;
    requires org.apache.poi.ooxml;
    requires org.apache.commons.io;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;

    requires com.johnpickup.common;
    requires com.johnpickup.gpx;
    requires com.johnpickup.parser;

    opens com.johnpickup.app.javafx;
    exports com.johnpickup.app.javafx;
    exports com.johnpickup.app.task;
    opens com.johnpickup.app.task;
}