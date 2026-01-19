package sgu.fit.tinhthue.strategy;

import sgu.fit.tinhthue.product.Product;
import sgu.fit.tinhthue.product.ProductState;

import java.math.BigDecimal;

public class ExciseTax implements TaxStrategy {
    private final BigDecimal exciseRate;

    public ExciseTax(BigDecimal exciseRate) {
        this.exciseRate = exciseRate;
    }

    @Override
    public String name() {
        return "EXCISE";
    }

    @Override
    public BigDecimal rate(Product product) {
        if (product.getState() == ProductState.TAX_EXEMPT) return BigDecimal.ZERO;
        return exciseRate;
    }
}

