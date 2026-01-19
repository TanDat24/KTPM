package sgu.fit.thanhtoan.decorator;

import java.util.Objects;

public abstract class PaymentDecorator implements PaymentComponent {
    protected final PaymentComponent inner;

    protected PaymentDecorator(PaymentComponent inner) {
        this.inner = Objects.requireNonNull(inner, "inner");
    }

    @Override
    public String description() {
        return inner.description();
    }
}

