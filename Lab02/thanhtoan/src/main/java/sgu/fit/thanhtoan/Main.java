package sgu.fit.thanhtoan;

import sgu.fit.thanhtoan.decorator.BasicPayment;
import sgu.fit.thanhtoan.decorator.DiscountDecorator;
import sgu.fit.thanhtoan.decorator.FeeDecorator;
import sgu.fit.thanhtoan.decorator.PaymentComponent;
import sgu.fit.thanhtoan.decorator.RefundDecorator;
import sgu.fit.thanhtoan.payment.PaymentContext;
import sgu.fit.thanhtoan.strategy.BankTransferPayment;
import sgu.fit.thanhtoan.strategy.CreditCardPayment;
import sgu.fit.thanhtoan.strategy.PaypalPayment;

public class Main {
    public static void main(String[] args) {
        // Decorator: tạo khoản thanh toán và gắn thêm phí/giảm giá/hoàn tiền linh hoạt
        PaymentComponent payment = new BasicPayment(100);
        payment = new FeeDecorator(payment, 5);
        payment = new DiscountDecorator(payment, 10);
        payment = new RefundDecorator(payment, 2);

        // Strategy + State: chọn cách thanh toán runtime và theo dõi trạng thái
        PaymentContext ctx = new PaymentContext(new CreditCardPayment("4111-1111-1111-1111"));
        boolean ok1 = ctx.pay(payment);
        System.out.println("Method=CreditCard | " + payment.description() + " | total=" + payment.totalAmount()
                + " | state=" + ctx.getState().name() + " | ok=" + ok1);

        ctx.setStrategy(new PaypalPayment("user@example.com"));
        boolean ok2 = ctx.pay(payment);
        System.out.println("Method=PayPal     | " + payment.description() + " | total=" + payment.totalAmount()
                + " | state=" + ctx.getState().name() + " | ok=" + ok2);

        ctx.setStrategy(new BankTransferPayment("VN00-IBAN-DEMO"));
        boolean ok3 = ctx.pay(payment);
        System.out.println("Method=Transfer   | " + payment.description() + " | total=" + payment.totalAmount()
                + " | state=" + ctx.getState().name() + " | ok=" + ok3);
    }
}
