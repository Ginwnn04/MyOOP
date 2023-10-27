package MyOOP.Entity;
public class Customer extends Account{
    private String idCustomer;
    private String fullName;

    public Customer() {
    }

    public Customer(String username, String password, String idCustomer, String fullName) {
        super(username, password);
        this.idCustomer = idCustomer;
        this.fullName = fullName;
    }
    public Customer(String username, String password, boolean isAdmin, String idCustomer, String fullName) {
        super(username, password, isAdmin);
        this.idCustomer = idCustomer;
        this.fullName = fullName;
    }

}
