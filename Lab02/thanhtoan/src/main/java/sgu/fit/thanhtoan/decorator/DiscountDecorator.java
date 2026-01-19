package sgu.fit.thanhtoan.decorator;

public class DiscountDecorator extends PaymentDecorator {
    private final double discount;

    public DiscountDecorator(PaymentComponent inner, double discount) {
        super(inner);
        this.discount = discount;
    }

    @Override
    public double totalAmount() {
        return Math.max(0, inner.totalAmount() - discount);
    }

    @Override
    public String description() {
        return inner.description() + " - discount(" + discount + ")";
    }
}

