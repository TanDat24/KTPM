package sgu.fit.thanhtoan.strategy;

import java.util.Objects;

public class PaypalPayment implements PaymentStrategy {
    private final String email;

    public PaypalPayment(String email) {
        this.email = Objects.requireNonNull(email, "email");
    }

    @Override
    public boolean pay(double amount) {
        // Demo: giả lập thanh toán thành công nếu amount >= 10
        return amount >= 10;
    }
}

