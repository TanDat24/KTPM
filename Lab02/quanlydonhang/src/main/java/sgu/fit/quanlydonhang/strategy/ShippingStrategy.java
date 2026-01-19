package sgu.fit.quanlydonhang.strategy;

import sgu.fit.quanlydonhang.order.Order;

/**
 * Strategy interface for shipping.
 */
public interface ShippingStrategy {
    String getName();

    /**
     * @return shipping cost (simple demo)
     */
    double calculateCost(Order order);
}

