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
        this.lastName = fullName.substring(0, fullName.lastIndexOf(" "));
        this.firstName = fullName.substring(fullName.lastIndexOf(" ") + 1);
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
        this.lastName = fullName.substring(0, fullName.lastIndexOf(" "));
        this.firstName = fullName.substring(fullName.lastIndexOf(" ") + 1);
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