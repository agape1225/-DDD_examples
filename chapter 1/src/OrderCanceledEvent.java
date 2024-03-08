public class OrderCanceledEvent {
    private String orderNumber;

    public OrderCanceledEvent(String number){
        this.orderNumber = number;
    }

    public String setOrderNumber(){return orderNumber;}
}
