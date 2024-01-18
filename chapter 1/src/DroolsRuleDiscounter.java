public class DroolsRuleDiscounter implements RuleDiscounter{
    private KieContainer kContainer;
    public DroolsRuleDiscounter(){
        KieService ks = KieService.Factory.get();
        kContainer = ks.getKieClasspathContainer();
    }

    @Override
    public Money applyRules(Customer customer, List<OrderLine> orderLines){
        
    }
}
