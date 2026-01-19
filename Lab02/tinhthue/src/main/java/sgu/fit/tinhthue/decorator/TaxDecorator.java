package sgu.fit.tinhthue.decorator;

import sgu.fit.tinhthue.product.Product;
import sgu.fit.tinhthue.strategy.TaxStrategy;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class TaxDecorator implements PriceComponent {
    protected final PriceComponent inner;
    protected final Product product;
    protected final TaxStrategy strategy;

    protected TaxDecorator(PriceComponent inner, Product product, TaxStrategy strategy) {
        this.inner = Objects.requireNonNull(inner, "inner");
        this.product = Objects.requireNonNull(product, "product");
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    @Override
    public BigDecimal total() {
        BigDecimal subtotal = inner.total();
        BigDecimal tax = subtotal.multiply(strategy.rate(product));
        return subtotal.add(tax);
    }
}

