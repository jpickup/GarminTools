module com.johnpickup.parser {
    requires lombok;
    requires antlr4.runtime;
    requires com.johnpickup.common;
    exports com.johnpickup.garmin.parser;
}