package sgu.fit.tinhthue.strategy;

import sgu.fit.tinhthue.product.Product;
import sgu.fit.tinhthue.product.ProductState;

import java.math.BigDecimal;

public class VATTax implements TaxStrategy {
    private final BigDecimal vatRate;

    public VATTax(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    @Override
    public String name() {
        return "VAT";
    }

    @Override
    public BigDecimal rate(Product product) {
        if (product.getState() == ProductState.TAX_EXEMPT) return BigDecimal.ZERO;
        return vatRate;
    }
}
