@Service
public class OrderCanceledEventHandler {
    private RefundService refundService;

    public OrderCanceledEventHandler(RefundService refundService){
        this.refundService = refundService;
    }

    //@EventListener(OrderCanceledEvent.class)
    @TransactionalEventListener(
            classes = OrderCanceledEvent.class;
            phase = TransactionPhase.AFTER_COMMIT;
    )
    public void handle(OrderCanceledEvent event){
        refundService.refund(event.getOrderNumber());
    }

}
