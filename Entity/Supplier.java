package DoAnOOP.Entity;

public class Supplier {
    private String idSupplier;
    private String nameSupplier;
    private String address;
    private String phone;

    public Supplier() {
        this.idSupplier = "";
        this.nameSupplier = "";
        this.address = "";
        this.phone = "";
    }

    public Supplier(String nameSupplier, String address, String phone) {
        this.idSupplier = generateId();
        this.nameSupplier = nameSupplier;
        this.address = address;
        this.phone = phone;
    }

    private String generateId() {
        return "NCC-" + (int) (Math.random() * 100000);
    }

    public Supplier(String idSupplier, String nameSupplier, String address, String phone) {
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
        this.address = address;
        this.phone = phone;
    }

    public void show() {
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", idSupplier, nameSupplier, address, phone);
    }

    public String prinToFile() {
        return idSupplier + "|" + nameSupplier + "|" + address + "|" + phone + "\n";
    }

    public String getIdSupplier() {
        return idSupplier;
    }
}