package sgu.fit.tinhthue.strategy;

import sgu.fit.tinhthue.product.Product;

import java.math.BigDecimal;

public interface TaxStrategy {
    String name();

    /**
     * @return tax rate as a decimal fraction. Example: 0.1 means 10%.
     */
    BigDecimal rate(Product product);
}

