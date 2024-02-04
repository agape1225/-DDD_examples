public class Address {
    private String address1;
    private String address2;
    private String zipcode;

    //데이터의 무결성을 위해 생성자 생성
    public Address(String address1, String address2, String zipcode){
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }
}
