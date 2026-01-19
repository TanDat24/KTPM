package sgu.fit.thanhtoan.payment;

public interface PaymentState {
    void handle(PaymentContext context);
    String name();
}

