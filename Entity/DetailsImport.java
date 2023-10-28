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

    public DetailsImport(int type, String idBillImport, String nameProduct, String unit, int quantity, long importPrice) {
        this.idBillImport = idBillImport;
        this.idProduct = generateIdProduct(type);
        this.nameProduct = nameProduct;
        this.unit = unit;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.totalPrice = importPrice * quantity;
    }
    public String generateIdProduct(int type) {
        // 1 = Foods
        // 2 = Drinks
        String firstID = "";
        if (type == 1) {
            firstID = "FD";
        }
        else {
            firstID = "DK";
        }
        return firstID + "-" + (int)(Math.random() * 100000000);
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

    public String getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setIsDelete(boolean flag) {
        isDelete = flag;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getUnit() {
        return unit;
    }

    public long getImportPrice() {
        return importPrice;
    }
}
