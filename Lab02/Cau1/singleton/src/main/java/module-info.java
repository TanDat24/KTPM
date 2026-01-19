module sgu.fit.singleton {
    requires javafx.controls;
    requires javafx.fxml;


    opens sgu.fit.singleton to javafx.fxml;
    exports sgu.fit.singleton;
}