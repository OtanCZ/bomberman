module com.bomberman.bomberman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bomberman to javafx.fxml;
    exports com.bomberman;
}