package sgu.fit.tinhthue.decorator;

import sgu.fit.tinhthue.product.Product;
import sgu.fit.tinhthue.strategy.TaxStrategy;

public class VATDecorator extends TaxDecorator {
    public VATDecorator(PriceComponent inner, Product product, TaxStrategy strategy) {
        super(inner, product, strategy);
    }
}

