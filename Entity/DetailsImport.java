package DoAnOOP.Entity;


public class DetailsImport {
    private String idBillImport;
    private String idProduct;
    private String nameProduct;
    private String unit;
    private int quantity;
    private long importPrice;
    private long totalPrice;
    private boolean isDelete;



    public DetailsImport(String idBillImport, String idProduct, String nameProduct, String unit, int quantity, long importPrice) {
        this.idBillImport = idBillImport;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.unit = unit;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.totalPrice = importPrice * quantity;
    }

    // Khi nhap du lieu tu file vao thi luc do chua tao duoc id bill
    public DetailsImport(String idProduct, String nameProduct, String unit, int quantity, long importPrice) {
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


    public String printToFile() {
        return idBillImport + "|" + idProduct + "|" + nameProduct + "|" + unit + "|" + quantity + "|" + importPrice + "|";
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setIdBillImport(String idBillImport) {
        this.idBillImport = idBillImport;
    }
}
