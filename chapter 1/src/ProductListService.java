public class ProductListService {
    public Page<Product> getProductOfCategory(Long categoryId, int page, int size){
        Category category = categoryRepository.findById(categoryId);
        List<Product> products = productRepository.findByCategory(category.getId(), page, size);
    }
}
