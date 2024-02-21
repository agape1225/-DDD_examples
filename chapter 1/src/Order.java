@Entity
@Table
public class Order {
    @EmbeddedId
    private OrderNo number;

    @Version
    private long version;
    private Orderer orderer;
    private Money totalAmounts;
    private List<OrderLine> orderLines;
    private List<Cupon> usedCupons;

    public void calculateAmounts(DiscountCalculationService disCalSvc, MemberGrade grade){
        Money totalAmounts = getTotalAmounts();
        Money discountAmounts = disCalSvc.calculateDiscountAmounts(this.orderLines, this.coupons, grade);
        this.paymentAmounts = totalAmounts.minus(discountAmounts);
    }

    private Money calculatePayAmount(){
        Money discount = usedCupons.map(copon -> calculateDiscount(cupon))
                .reduce(Money(0), (v1, v2) -> v1.add(v2));
        Money membershipDiscount = calculateDiscount(orderer.getMember().getGrade());

        return totalAmounts.minus(discount).minus(memberShipDiscount);
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo, boolean useNewShippingAddrAsMemberAddr){
        if(useNewShippingAddrAsMemberAddr){
            //한 애그리거트 내부에서 다른 애그리거트에 접근할 수 있으면,
            //구현이 쉬워진다는 것 때문에 다른 애그리거트의 상태를 변경하는
            //유혹에 빠지기 쉽다.
            orderer.getMember().changeAddress(newShippingInfo.getAddress);
        }
        this.shippingInfo = newShippingInfo;
    }
    public void shipTo(ShippingInfo newShippingInfo,
                       boolean useNewShippingAddrAsMemberAddr){
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
        if(useNewShippingAddrAsMemberAddr){
            //다른 애그리거트의 상태를 변경하면 안 됨!
            orderer.getMember().changeAddress(newShippingInfo.getAddress);
        }
    }

    public void changeOrderLines(List<OrderLine> newLines){
        orderLines.changeOrderLines(newLines);
        this.totalAmounts = orderLines.getTotalAmounts();
    }

    private void calculateTotalAmounts(){
        int sum = orderLines.stream().mapToInt(x -> x.getAmounts()).sum();
        this.totalAmounts = new Money(sum);
    }

    private ShippingInfo shippingInfo;
    private OrderState state;
    private String orderNumber;
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(obj.getClass() != Order.class) return false;
        Order order = (Order) obj;
        if(this.orderNumber == null) return false;
        return this.orderNumber.equals(other.orderNumber);
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        return result
    }

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo){
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void setShippingInfo(ShippingInfo shippingInfo){
        if(shippingInfo == null)
            throw new IllegalArgumentException("no ShippingInfo");
        this.shippingInfo = shippingInfo;
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines){
        if(orderLines == null || orderLines.isEmpty()){
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts(){
        int sum = orderLines.stream().mapToInt(x -> x.getAmounts()).sum();
        this.totalAmounts = new Money(sum);
    }

    public void changeShipped() {}
    public void changeShippingInfo(ShippingInfo newShipping){
        verifyNotYetShipped();
        setShippingInfo(newShipping);
    }
    public void cancel(){
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    private void verifyNotYetShipped() {
        if(state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING)
            throw new IllegalStateException("already shipped");
    }

    public void completePayment(){}



    public void changeShippingInfo(ShippingInfo newShippingInfo){
        if(!state.isShippingChangeable()){
            throw new IllegalStateException("can't change shipping in " + state);
        }
        this.shippingInfo = newShippingInfo;
    }

    private boolean isShippingChangeable(){
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }
}

