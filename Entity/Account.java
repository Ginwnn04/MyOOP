package DoAnOOP.Entity;

public class Account {
    private String username;
    protected String password;
    private boolean isAdmin;

    public Account() {
        this.username = "";
        this.password = "";
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Account(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
