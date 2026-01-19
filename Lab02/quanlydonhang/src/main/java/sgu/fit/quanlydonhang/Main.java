package sgu.fit.quanlydonhang;

import sgu.fit.quanlydonhang.decorator.BasicOrder;
import sgu.fit.quanlydonhang.decorator.GiftWrapDecorator;
import sgu.fit.quanlydonhang.decorator.InsuranceDecorator;
import sgu.fit.quanlydonhang.decorator.OrderComponent;
import sgu.fit.quanlydonhang.order.Order;
import sgu.fit.quanlydonhang.strategy.ExpressShipping;

public class Main {
    public static void main(String[] args) {
        // Create order (State starts at NEW)
        Order order = new Order("ORD-001", 200_000);

        // Strategy: choose shipping method at runtime
        order.setShippingStrategy(new ExpressShipping());

        // Decorator: add optional services
        OrderComponent pricing = new BasicOrder(order.getBaseCost());
        pricing = new InsuranceDecorator(pricing);
        pricing = new GiftWrapDecorator(pricing);
        order.setPricingComponent(pricing);

        // State transitions
        order.process();
        order.deliver();

        // Output
        System.out.println("OrderId: " + order.getId());
        System.out.println("State: " + order.getStateName());
        System.out.println("Breakdown: " + order.getCostBreakdown());
        System.out.println("Shipping cost: " + order.getShippingCost());
        System.out.println("Total cost: " + order.getTotalCost());

        System.out.println("---- Logs ----");
        for (String log : order.getLogs()) {
            System.out.println(log);
        }
    }
}
