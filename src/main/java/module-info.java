module com.bomberman.bomberman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bomberman to javafx.fxml;
    exports com.bomberman;
    exports com.bomberman.controllers;
    opens com.bomberman.controllers to javafx.fxml;
}