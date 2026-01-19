package sgu.fit.thanhtoan.decorator;

public class RefundDecorator extends PaymentDecorator {
    private final double refund;

    public RefundDecorator(PaymentComponent inner, double refund) {
        super(inner);
        this.refund = refund;
    }

    @Override
    public double totalAmount() {
        return Math.max(0, inner.totalAmount() - refund);
    }

    @Override
    public String description() {
        return inner.description() + " - refund(" + refund + ")";
    }
}

