module com.mycompany.api.github {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    exports com.mycompany.api.github;
    exports com.mycompany.api.github.domain;
    exports com.mycompany.api.github.service;
    exports com.mycompany.api.github.ui;
    exports com.mycompany.api.github.util;

    opens com.mycompany.api.github to javafx.fxml;
}
