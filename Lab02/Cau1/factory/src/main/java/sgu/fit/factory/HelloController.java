package sgu.fit.factory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        DonVi donVi = DonViProvider.layDonVi();
        NhanSu ns = donVi.taoNhanSu();
        welcomeText.setText(ns.getLoai() + " - " + ns.lamViec());
    }
}