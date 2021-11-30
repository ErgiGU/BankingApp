module pleasefivebank.bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires mongo.java.driver;
    requires java.logging;

    opens pleasefivebank to javafx.fxml;
    opens pleasefivebank.EntryPage to javafx.fxml;
    opens pleasefivebank.Registration to javafx.fxml;
    exports pleasefivebank;
}