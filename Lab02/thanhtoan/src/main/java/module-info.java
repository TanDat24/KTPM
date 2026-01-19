module sgu.fit.thanhtoan {
    requires javafx.controls;
    requires javafx.fxml;


    opens sgu.fit.thanhtoan to javafx.fxml;

    exports sgu.fit.thanhtoan;
    exports sgu.fit.thanhtoan.payment;
    exports sgu.fit.thanhtoan.strategy;
    exports sgu.fit.thanhtoan.decorator;
}