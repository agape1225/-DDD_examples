import java.util.ArrayList;

class Test{
    @Transactional
    public OrderNo placeOrder(OrderRequest orderRequest){
        List<ValidationError> errors = new ArrayList<>();
        if(orderRequest == null){
            errors.add(ValidationError.of("empty"));
        }else{
            if(orderRequest.getOrderMemberId() == null)
                errors.add(ValidationError.of("ordererMemberId", "empty"));
            if(orderRequest.getOrderProducts() == null)
                errors.add(ValidationError.of("orderProducts", "empty"));
        }
        if(!errors.isEmpty()) throw new ValidationErrorException(errors);
    }
}