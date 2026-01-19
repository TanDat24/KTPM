package sgu.fit.thanhtoan.payment;

public class SuccessState implements PaymentState {
    @Override
    public void handle(PaymentContext context) {
        // Thành công: không làm gì thêm.
    }

    @Override
    public String name() {
        return "SUCCESS";
    }
}

