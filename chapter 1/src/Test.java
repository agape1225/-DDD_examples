
Member member = memberRepository.findById(orderId);
List<Order> orders = orderRepository.findByOrderer(orderId);
List<OrderView> dtos = orders.stream().map(order ->{
    ProductId prodiId = order.getOrderLines().get().getProductId();
    Product product = productRepository.findById(prodId);
    return new OrderView(order, member, product);
}).collect(toList());