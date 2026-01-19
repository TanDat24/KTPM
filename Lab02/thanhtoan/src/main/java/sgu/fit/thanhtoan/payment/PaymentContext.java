package sgu.fit.thanhtoan.payment;

import java.util.Objects;

import sgu.fit.thanhtoan.decorator.PaymentComponent;
import sgu.fit.thanhtoan.strategy.PaymentStrategy;

public class PaymentContext {
    private PaymentState state;
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy");
        this.state = new PendingState();
    }

    public PaymentState getState() {
        return state;
    }

    public void setState(PaymentState state) {
        this.state = Objects.requireNonNull(state, "state");
    }

    public PaymentStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public boolean pay(PaymentComponent payment) {
        Objects.requireNonNull(payment, "payment");
        setState(new PendingState());
        state.handle(this);

        boolean ok = strategy.pay(payment.totalAmount());
        setState(ok ? new SuccessState() : new FailedState());
        state.handle(this);
        return ok;
    }
}

