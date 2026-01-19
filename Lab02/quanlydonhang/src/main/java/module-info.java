module sgu.fit.quanlydonhang {
    requires javafx.controls;
    requires javafx.fxml;


    opens sgu.fit.quanlydonhang to javafx.fxml;
    exports sgu.fit.quanlydonhang;

    exports sgu.fit.quanlydonhang.order;
    exports sgu.fit.quanlydonhang.strategy;
    exports sgu.fit.quanlydonhang.decorator;
}