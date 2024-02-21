import java.util.HashMap;

public class TransferService {
    public void transfer(Account fromAcc, Account toAcc, Money amounts){
        fromAcc.withdraw(amounts);
        toAcc.credit(amounts);
    }
}

Map<String, Object> hints = new HashMap<>();
hints.put("javax.persistence.lock.timeout", 2000);
Order order = entityManager.find(Order.class, orderNo, LockModeType.PESSIMISIC_WRITE, hints);