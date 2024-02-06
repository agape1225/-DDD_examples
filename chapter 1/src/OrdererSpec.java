public class OrdererSpec implements Speficiation<Order>{
    private String orderId;

    public OrdererSpec(String orderId){
        this.orderId = orderId;
    }

    public boolean isSatisfiedBy(Order agg){
        return agg.getOrdererId().getMemberId().getId().equals(orderId);
    }
}
