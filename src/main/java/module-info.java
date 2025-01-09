module itsgwynniie.appbanco {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens itsgwynniie.appbanco to javafx.fxml;
    exports itsgwynniie.appbanco;
}
