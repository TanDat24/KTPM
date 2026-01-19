package sgu.fit.thanhtoan.decorator;

public class FeeDecorator extends PaymentDecorator {
    private final double fee;

    public FeeDecorator(PaymentComponent inner, double fee) {
        super(inner);
        this.fee = fee;
    }

    @Override
    public double totalAmount() {
        return inner.totalAmount() + fee;
    }

    @Override
    public String description() {
        return inner.description() + " + fee(" + fee + ")";
    }
}

