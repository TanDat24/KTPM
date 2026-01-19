package sgu.fit.quanlydonhang.decorator;

public abstract class OrderDecorator implements OrderComponent {
    protected final OrderComponent inner;

    protected OrderDecorator(OrderComponent inner) {
        this.inner = inner;
    }

    @Override
    public double getCost() {
        return inner.getCost();
    }

    @Override
    public String getDescription() {
        return inner.getDescription();
    }
}

