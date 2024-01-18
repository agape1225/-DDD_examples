@Repository
public class JpaOrderViewDao implements OrderViewDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OrderView> selectByOrder(String orderId){
        string selectQuery =
    }
}
