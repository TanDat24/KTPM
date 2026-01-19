package sgu.fit.singleton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private void onHelloButtonClick() {
        // Obtain multiple references to the singleton
        Warehouse w1 = Warehouse.getInstance();
        Warehouse w2 = Warehouse.getInstance();

        boolean sameInstance = (w1 == w2);
        int before = w1.getQuantity();

        // Mutate via one reference and read via the other
        w1.increase(10);
        boolean decOk = w2.decrease(5);
        int after = w1.getQuantity();

        welcomeText.setText("Same instance: " + sameInstance + "\nBefore: " + before + " After: " + after + " Decrease OK: " + decOk);
    }
}

