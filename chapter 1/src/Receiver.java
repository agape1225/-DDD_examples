@Embeddable
public class Receiver {
    private String name;
    private String phoneNumber;

    protected Receiver(){}

    public Receiver(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}
