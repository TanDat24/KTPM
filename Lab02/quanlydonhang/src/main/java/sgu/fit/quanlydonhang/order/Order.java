package sgu.fit.quanlydonhang.order;

import sgu.fit.quanlydonhang.decorator.BasicOrder;
import sgu.fit.quanlydonhang.decorator.OrderComponent;
import sgu.fit.quanlydonhang.strategy.ShippingStrategy;
import sgu.fit.quanlydonhang.strategy.StandardShipping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Order context (State) + uses ShippingStrategy.
 * Extras (insurance/gift wrap) are handled via the Decorator graph stored in {@link #pricingComponent}.
 */
public class Order {
    private final String id;
    private final double baseCost;

    private OrderState state;
    private ShippingStrategy shippingStrategy;

    private OrderComponent pricingComponent;

    private final List<String> logs = new ArrayList<>();

    public Order(String id, double baseCost) {
        this.id = Objects.requireNonNull(id);
        this.baseCost = baseCost;

        this.state = new NewOrderState();
        this.shippingStrategy = new StandardShipping();
        this.pricingComponent = new BasicOrder(baseCost);

        addLog("Order created. State=NEW");
    }

    public String getId() {
        return id;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public String getStateName() {
        return state.getName();
    }

    public ShippingStrategy getShippingStrategy() {
        return shippingStrategy;
    }

    public void setShippingStrategy(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = Objects.requireNonNull(shippingStrategy);
        addLog("Shipping method set to: " + shippingStrategy.getName());
    }

    public void setState(OrderState state) {
        this.state = Objects.requireNonNull(state);
        addLog("State changed to: " + state.getName());
    }

    public void process() {
        state.process(this);
    }

    public void deliver() {
        state.deliver(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public double getShippingCost() {
        return shippingStrategy.calculateCost(this);
    }

    public void setPricingComponent(OrderComponent pricingComponent) {
        this.pricingComponent = Objects.requireNonNull(pricingComponent);
    }

    public OrderComponent getPricingComponent() {
        return pricingComponent;
    }

    public double getTotalCost() {
        return pricingComponent.getCost() + getShippingCost();
    }

    public String getCostBreakdown() {
        return pricingComponent.getDescription() + "; shipping=" + shippingStrategy.getName();
    }

    public void addLog(String message) {
        logs.add(message);
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }
}

