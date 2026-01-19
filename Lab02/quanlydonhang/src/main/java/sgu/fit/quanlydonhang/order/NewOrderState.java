package sgu.fit.quanlydonhang.order;

/**
 * New-created order state.
 */
public class NewOrderState implements OrderState {
    @Override
    public String getName() {
        return "NEW";
    }

    @Override
    public void process(Order order) {
        order.addLog("Order is being processed.");
        order.setState(new ProcessingOrderState());
    }

    @Override
    public void deliver(Order order) {
        order.addLog("Cannot deliver a NEW order. Please process first.");
    }

    @Override
    public void cancel(Order order) {
        order.addLog("Order is cancelled.");
        order.setState(new CancelledOrderState());
    }
}

