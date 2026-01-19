package sgu.fit.quanlydonhang.order;

/**
 * Final state: cancelled.
 */
public class CancelledOrderState implements OrderState {
    @Override
    public String getName() {
        return "CANCELLED";
    }

    @Override
    public void process(Order order) {
        order.addLog("Cannot process a CANCELLED order.");
    }

    @Override
    public void deliver(Order order) {
        order.addLog("Cannot deliver a CANCELLED order.");
    }

    @Override
    public void cancel(Order order) {
        order.addLog("Order is already CANCELLED.");
    }
}

