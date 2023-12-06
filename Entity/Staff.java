package DoAnOOP.Entity;

import java.util.Arrays;

public class Staff {
    private String idStaff;
    private String lastName;// họ
    private String firstName;// tên
    private String phone;

    public Staff() {
        this.idStaff = "";
        this.lastName = "";
        this.firstName = "";
        this.phone = "";
    }

    public Staff(String fullName, String phone) {
        this.idStaff = generateId();
        String[] name = fullName.split(" ");
        if (name.length >= 2) {
            this.lastName = name[0];
            this.firstName = String.join(" ", Arrays.copyOfRange(name, 1, name.length));
        } else {
            this.lastName = fullName;
            this.firstName = "";
        }
        this.phone = phone;
    }

    public Staff(String idStaff, String lastName, String firstName, String phone) {
        this.idStaff = idStaff;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
    }

    public String generateId() {
        return "NV-" + (int) (Math.random() * 100000);
    }

    public String getIdStaff() {
        return idStaff;
    }

    public void setFullName(String fullName) {
        String[] name = fullName.split(" ");
        if (name.length >= 2) {
            this.lastName = name[0];
            this.firstName = String.join(" ", Arrays.copyOfRange(name, 1, name.length));
        } else {
            this.lastName = fullName;
            this.firstName = "";
        }
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public void showStaff() {
        System.out.format("%-15s %-15s %-15s %-15s \n", idStaff, lastName, firstName, phone);
    }

    public String printToFile() {
        return idStaff + "|" + lastName + "|" + firstName + "|" + phone + "\n";
    }

}
