package sgu.fit.thanhtoan.payment;

public class FailedState implements PaymentState {
    @Override
    public void handle(PaymentContext context) {
        // Thất bại: không làm gì thêm.
    }

    @Override
    public String name() {
        return "FAILED";
    }
}

