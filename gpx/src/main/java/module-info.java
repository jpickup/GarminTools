module com.johnpickup.gpx {
    requires lombok;
    requires jakarta.xml.bind;
    requires com.johnpickup.common;
    opens com.johnpickup.gpx;
    exports com.johnpickup.gpx;
}