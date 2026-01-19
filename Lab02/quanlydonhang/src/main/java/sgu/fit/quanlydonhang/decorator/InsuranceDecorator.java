package sgu.fit.quanlydonhang.decorator;

public class InsuranceDecorator extends OrderDecorator {
    public InsuranceDecorator(OrderComponent inner) {
        super(inner);
    }

    @Override
    public double getCost() {
        return super.getCost() + 10000;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + insurance";
    }
}

