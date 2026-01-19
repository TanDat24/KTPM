package sgu.fit.singleton;

public class Warehouse {
    private int quantity;

    private static final Warehouse INSTANCE = new Warehouse();

    private Warehouse() {
        this.quantity = 100;
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public synchronized int getQuantity() {
        return quantity;
    }

    public synchronized void increase(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }

    public synchronized boolean decrease(int amount) {
        if (amount <= 0) return false;
        if (amount > quantity) return false;
        quantity -= amount;
        return true;
    }
}

