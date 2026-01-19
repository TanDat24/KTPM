package sgu.fit.thanhtoan.payment;

public class PendingState implements PaymentState {
    @Override
    public void handle(PaymentContext context) {
        // Chờ thanh toán: không làm gì ngoài việc xác nhận trạng thái.
    }

    @Override
    public String name() {
        return "PENDING";
    }
}

