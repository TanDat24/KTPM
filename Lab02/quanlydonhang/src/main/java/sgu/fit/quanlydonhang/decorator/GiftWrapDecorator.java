package sgu.fit.quanlydonhang.decorator;

public class GiftWrapDecorator extends OrderDecorator {
    public GiftWrapDecorator(OrderComponent inner) {
        super(inner);
    }

    @Override
    public double getCost() {
        return super.getCost() + 5000;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + gift wrap";
    }
}

