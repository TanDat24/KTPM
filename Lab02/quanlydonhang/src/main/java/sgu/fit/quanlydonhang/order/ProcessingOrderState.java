package sgu.fit.quanlydonhang.order;

/**
 * Order is being prepared / handled.
 */
public class ProcessingOrderState implements OrderState {
    @Override
    public String getName() {
        return "PROCESSING";
    }

    @Override
    public void process(Order order) {
        order.addLog("Order is already PROCESSING.");
    }

    @Override
    public void deliver(Order order) {
        order.addLog("Order delivered.");
        order.setState(new DeliveredOrderState());
    }

    @Override
    public void cancel(Order order) {
        order.addLog("Order is cancelled while processing.");
        order.setState(new CancelledOrderState());
    }
}
