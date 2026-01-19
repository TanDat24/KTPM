package sgu.fit.tinhthue.decorator;

import java.math.BigDecimal;
import java.util.Objects;

public class BasePrice implements PriceComponent {
    private final BigDecimal base;

    public BasePrice(BigDecimal base) {
        this.base = Objects.requireNonNull(base, "base");
    }

    @Override
    public BigDecimal total() {
        return base;
    }
}

