package MyOOP.Entity;
public class Employee extends Account{
    private String idEmployee;
    private String fullName;

    public Employee() {
        this.idEmployee = "";
        this.fullName = "";
    }

    public Employee(String username, String password, String idEmployee, String fullName) {
        super(username, password);
        this.idEmployee = idEmployee;
        this.fullName = fullName;
    }
    public Employee(String username, String password, boolean isAdmin, String idEmployee, String fullName) {
        super(username, password, isAdmin);
        this.idEmployee = idEmployee;
        this.fullName = fullName;
    }
}
