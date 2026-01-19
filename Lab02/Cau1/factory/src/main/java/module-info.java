module sgu.fit.factory {
    requires javafx.controls;
    requires javafx.fxml;


    opens sgu.fit.factory to javafx.fxml;
    exports sgu.fit.factory;
}