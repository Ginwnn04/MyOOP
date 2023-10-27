package DoAnOOP.Entity;

public class DetailsImport {
    private String idImportProduct;
    private String idProduct;
    private String unit;
    private int quantity;
    private long importPrice;
    private long totalPrice;

    public DetailsImport(String idImportProduct, String idProduct, String unit, int quantity, long importPrice) {
        this.idImportProduct = idImportProduct;
        this.idProduct = idProduct;
        this.unit = unit;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.totalPrice = importPrice * quantity;
    }
}
