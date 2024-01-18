public class RegisterProductService {
    public ProductId registerNewProduct(NewProductRequest req){
        Store store = storeRepository.findBVyId(req.getStoreId());
        checKNull(store);
        ProductId id = productRepository.nextId();
        Product product = store.createProduct(id, ...);
        productRepository.save(product);
        return id;
    }
}
