public class Item {
    private int orderNumber;
    private int itemNumber;
    private int quantity;
    private double unitPrice;

    public Item(int orderNumber, int itemNumber, int quantity, double unitPrice) {
        this.orderNumber = orderNumber;
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
