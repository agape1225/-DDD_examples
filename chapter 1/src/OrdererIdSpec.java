import java.util.function.Predicate;

public class OrdererIdSpec implements Specification<OrderSummary>{
    private String ordererId;
    public OrdererIdSpec(String ordererId){
        this.ordererId = ordererId;
    }

    @Override
    public Predicate toPredicate(Root<OrderSummary> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBVuilder cb){
        return cb.equal(root.get(OrderSummary_.ordererId), orderId);
    }
}
