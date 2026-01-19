package sgu.fit.tinhthue;

import sgu.fit.tinhthue.decorator.BasePrice;
import sgu.fit.tinhthue.decorator.LuxuryTaxDecorator;
import sgu.fit.tinhthue.decorator.PriceComponent;
import sgu.fit.tinhthue.decorator.VATDecorator;
import sgu.fit.tinhthue.product.Product;
import sgu.fit.tinhthue.product.ProductState;
import sgu.fit.tinhthue.strategy.ExciseTax;
import sgu.fit.tinhthue.strategy.LuxuryTax;
import sgu.fit.tinhthue.strategy.VATTax;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Product normal = new Product("Milk", new BigDecimal("100000"), ProductState.NORMAL);
        Product luxury = new Product("Watch", new BigDecimal("2000000"), ProductState.LUXURY);
        Product exempt = new Product("Book", new BigDecimal("150000"), ProductState.TAX_EXEMPT);

        var vat10 = new VATTax(new BigDecimal("0.10"));
        var excise5 = new ExciseTax(new BigDecimal("0.05"));
        var luxuryTax20 = new LuxuryTax(new BigDecimal("0.20"));

        // Decorator: stack taxes on top of base price, using Strategy to compute the rate.
        printPrice("NORMAL + VAT + EXCISE", normal,
                new VATDecorator(new VATDecorator(new BasePrice(normal.getBasePrice()), normal, vat10), normal, excise5));

        printPrice("LUXURY + VAT + EXCISE + LUXURY", luxury,
                new LuxuryTaxDecorator(
                        new VATDecorator(new VATDecorator(new BasePrice(luxury.getBasePrice()), luxury, vat10), luxury, excise5),
                        luxury,
                        luxuryTax20));

        printPrice("TAX_EXEMPT (no VAT/excise/luxury)", exempt,
                new LuxuryTaxDecorator(new VATDecorator(new BasePrice(exempt.getBasePrice()), exempt, vat10), exempt, luxuryTax20));
    }

    private static void printPrice(String title, Product product, PriceComponent component) {
        System.out.println("----- " + title + " -----");
        System.out.println("Product: " + product.getName() + " (state=" + product.getState() + ")");
        System.out.println("Base:   " + product.getBasePrice());
        System.out.println("Total:  " + component.total());
        System.out.println();
    }
}
