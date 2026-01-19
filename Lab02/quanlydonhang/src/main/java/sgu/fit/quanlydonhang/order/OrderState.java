package sgu.fit.quanlydonhang.order;

/**
 * State interface for order lifecycle.
 */
public interface OrderState {
    String getName();

    void process(Order order);

    void deliver(Order order);

    void cancel(Order order);
}

