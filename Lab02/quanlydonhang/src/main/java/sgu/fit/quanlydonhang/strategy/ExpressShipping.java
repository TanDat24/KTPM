package sgu.fit.quanlydonhang.strategy;

import sgu.fit.quanlydonhang.order.Order;

public class ExpressShipping implements ShippingStrategy {
    @Override
    public String getName() {
        return "Express";
    }

    @Override
    public double calculateCost(Order order) {
        return 35000; // demo flat cost
    }
}

