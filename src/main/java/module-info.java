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
    requires iban4j;
    requires com.jfoenix;

    requires passay;
    requires mail;
    requires logback.classic;
    requires slf4j.api;

    opens pleasefivebank to javafx.fxml;
    opens pleasefivebank.EntryPage to javafx.fxml;
    opens pleasefivebank.UserPage to javafx.fxml;
    exports pleasefivebank;
    opens pleasefivebank.Objects to javafx.base, javafx.fxml;
}