package MyOOP.Entity;

public class DetailsImport {
    private String idBillImport;
    private String idProduct;
    private String nameProduct;
    private String unit;
    private int quantity;
    private long importPrice;
    private long totalPrice;
    private boolean isDelete = false;

    public DetailsImport(String idBillImport, String idProduct, String nameProduct, String unit, int quantity, long importPrice) {
        this.idBillImport = idBillImport;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.unit = unit;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.totalPrice = importPrice * quantity;
    }

    public void printDetail() {
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                            + colSpace + "s %-"
                            + colSpace + "s %-"
                            + colSpace + "s %-"
                            + colSpace + "d %-"
                            + colSpace + "d\n", idProduct, nameProduct, unit, quantity, importPrice, totalPrice);

    }

    public long getTotalPrice() {
        return totalPrice;
    }

}
