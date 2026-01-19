package sgu.fit.quanlydonhang.strategy;

import sgu.fit.quanlydonhang.order.Order;

public class StandardShipping implements ShippingStrategy {
    @Override
    public String getName() {
        return "Standard";
    }

    @Override
    public double calculateCost(Order order) {
        return 15000; // demo flat cost
    }
}

