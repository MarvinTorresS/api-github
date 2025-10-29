module com.mycompany.api.github {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires java.base;

    exports com.mycompany.api.github;
    exports com.mycompany.api.github.domain;
    exports com.mycompany.api.github.service;
    exports com.mycompany.api.github.ui;

    opens com.mycompany.api.github to javafx.fxml;
    opens com.mycompany.api.github.ui to javafx.fxml; // 👈 Añade esta línea
}
