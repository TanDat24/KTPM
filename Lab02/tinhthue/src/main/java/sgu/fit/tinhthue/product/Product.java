package sgu.fit.tinhthue.product;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String name;
    private final BigDecimal basePrice;
    private ProductState state;

    public Product(String name, BigDecimal basePrice, ProductState state) {
        this.name = Objects.requireNonNull(name, "name");
        this.basePrice = Objects.requireNonNull(basePrice, "basePrice");
        this.state = Objects.requireNonNull(state, "state");
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public ProductState getState() {
        return state;
    }

    public void setState(ProductState state) {
        this.state = Objects.requireNonNull(state, "state");
    }
}

