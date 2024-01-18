public class ChangeOrderService {
    //두 개 이상의 애그리거트를 변경해야 하면,
    //응용 서비스에서 각 애그리거트의 상태를 변경한다.
    @Transactional
    public void changeShippingInfo(OrderId id, ShippingInfo newShippingInfo, boolean useNewShippingAddrAsMemberAddr){
        Order order = orderRepository.findbyId(id);
        if(order == null) throw new OrderNotFoundException();
        order.changeShippingInfo(newShippingInfo);
        if(useNewShippingAddrAsMemberAddr){
            //ID를 이용해서 참조하는 애그리거트를 구한다.
            Member member = memberRepository.findById(order.getOrderer().getMemberId());
            member.changeAddress(newShippingInfo.getAddress());
        }

    }
}
