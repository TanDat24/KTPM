package sgu.fit.thanhtoan.strategy;

import java.util.Objects;

public class BankTransferPayment implements PaymentStrategy {
    private final String iban;

    public BankTransferPayment(String iban) {
        this.iban = Objects.requireNonNull(iban, "iban");
    }

    @Override
    public boolean pay(double amount) {
        // Demo: giả lập thanh toán thành công nếu amount >= 1
        return amount >= 1;
    }
}

