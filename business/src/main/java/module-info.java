module com.johnpickup.business {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires org.slf4j;
    requires antlr4.runtime;
    requires fit;
    requires org.apache.poi.poi;
    requires biweekly;
    requires com.johnpickup.domain;
    requires com.johnpickup.common;
    requires jakarta.xml.bind;
    requires org.apache.poi.ooxml;
    requires org.apache.commons.io;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;

    opens com.johnpickup.app.javafx;
    //opens com.johnpickup.app.javafx to javafx.graphics, javafx.fxml;
    exports com.johnpickup.app.javafx;
    exports com.johnpickup.app.task;
    opens com.johnpickup.app.task;
}