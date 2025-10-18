public class Payment {
    private int paymentId;
    private Order order;
    private double amount;
    private String paymentMethod;

    public Payment(int paymentId, Order order, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", order=" + order +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }

    public void processPayment() {
        // Logic to process payment
        System.out.println("Processing payment of " + amount + " via " + paymentMethod);
    }
}
