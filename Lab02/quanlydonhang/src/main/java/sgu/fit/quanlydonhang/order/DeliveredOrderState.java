package sgu.fit.quanlydonhang.order;

/**
 * Final state: delivered.
 */
public class DeliveredOrderState implements OrderState {
    @Override
    public String getName() {
        return "DELIVERED";
    }

    @Override
    public void process(Order order) {
        order.addLog("Cannot process a DELIVERED order.");
    }

    @Override
    public void deliver(Order order) {
        order.addLog("Order is already DELIVERED.");
    }

    @Override
    public void cancel(Order order) {
        order.addLog("Cannot cancel a DELIVERED order.");
    }
}

