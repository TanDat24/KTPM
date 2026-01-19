module sgu.fit.tinhthue {
    requires javafx.controls;
    requires javafx.fxml;


    opens sgu.fit.tinhthue to javafx.fxml;
    exports sgu.fit.tinhthue;
}