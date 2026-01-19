package sgu.fit.thanhtoan.strategy;

import java.util.Objects;

public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = Objects.requireNonNull(cardNumber, "cardNumber");
    }

    @Override
    public boolean pay(double amount) {
        // Demo: giả lập thanh toán thành công nếu amount > 0
        return amount > 0;
    }
}

