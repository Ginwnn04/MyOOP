package DoAnOOP.Entity;

public class Customer {
    private String idCustomer;
    private String lastName;
    private String firstName;
    private String phone;

    public Customer(String fullName, String phone) {
        this.idCustomer = generateId();
        this.lastName = fullName.substring(0, fullName.lastIndexOf(" "));
        this.firstName = fullName.substring(fullName.lastIndexOf(" ") + 1);
        this.phone = phone;
    }

    public Customer(String idCustomer, String lastName, String firstName, String phone) {
        this.idCustomer = idCustomer;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
    }

    public void show() {
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", idCustomer, lastName, firstName, phone);
    }
    public String printToFile() {
        return idCustomer + "|" + lastName + "|" +  firstName + "|" + phone + "\n";
    }

    public String getphone(){
        return phone;
    }

    public String getidCustomer(){
        return idCustomer;
    }

    public String generateId() {
        return "KH-" + (int)(Math.random() * 1000000);
    }
}
