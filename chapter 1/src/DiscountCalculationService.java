public class DiscountCalculationService {
    public Money calculateDiscountAmounts(
            List<OrderLine> orderLines,
            List<Coupon> coupons,
            MemberGrade grade
    ){
        Money couponDiscount = cupons.stream()
                .map(cupon -> calculateDiscount(cupon))
                .reduce(Money(0), (v1, v2) -> v1.add(v2));

        Money membershipDiscount = calculateDiscount(orderer.getMember().getGrade());

        return couponDiscount.add(membershipDiscount);
    }
}
