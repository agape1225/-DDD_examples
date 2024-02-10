public class DeleteArticleService {
    public void delete(String userId, Long articleId){
        Article article = articleRepository.findById(articleId);
        checkArticleExistence(article);
        permissionService.checkDeletePermission(userId, article);
        article.markDeleted();
    }
}
