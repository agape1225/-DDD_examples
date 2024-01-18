import java.util.Arrays;

public class CalculateDiscountService {
    private RuleDiscounter ruleDiscounter;
    private DroolsRuleEngine ruleEngine;

    public CalculateDiscountService(RuleDiscounter ruleDiscounter){
        this.ruleDiscounter = ruleDiscounter;
    }

    public Money calculateDiscount(List<OrderLine> orderLines, String customerId){
        Customer customer = findCustomer(customerId);
        return ruleDiscounter.applyRules(customer, orderLines);
    }
}
