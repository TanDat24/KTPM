package sgu.fit.thanhtoan.decorator;

public class BasicPayment implements PaymentComponent {
    private final double amount;

    public BasicPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public double totalAmount() {
        return amount;
    }

    @Override
    public String description() {
        return "Basic payment";
    }
}

