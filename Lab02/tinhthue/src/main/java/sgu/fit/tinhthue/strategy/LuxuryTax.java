package sgu.fit.tinhthue.strategy;

import sgu.fit.tinhthue.product.Product;
import sgu.fit.tinhthue.product.ProductState;

import java.math.BigDecimal;

public class LuxuryTax implements TaxStrategy {
    private final BigDecimal luxuryRate;

    public LuxuryTax(BigDecimal luxuryRate) {
        this.luxuryRate = luxuryRate;
    }

    @Override
    public String name() {
        return "LUXURY";
    }

    @Override
    public BigDecimal rate(Product product) {
        if (product.getState() != ProductState.LUXURY) return BigDecimal.ZERO;
        return luxuryRate;
    }
}

