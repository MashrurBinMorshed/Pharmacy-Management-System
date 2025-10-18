import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<Medicine> items;
    private Date orderDate;


    public Order(int orderId, Customer customer, List<Medicine> items, Date orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Medicine> getItems() {
        return items;
    }

    public void setItems(List<Medicine> items) {
        this.items = items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", items=" + items +
                ", orderDate=" + orderDate +
                '}';
    }
}
