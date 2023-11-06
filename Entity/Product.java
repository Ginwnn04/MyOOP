package MyOOP.Entity;

public abstract class Product {
    protected String ID;
    protected String nameProduct;
    protected String unit;
    protected int quantity;
    protected int price;
    protected int priceImport;
    protected boolean isDelete;

    public Product() {
        this.ID = "";
        this.nameProduct = "";
        this.price = 0;
        this.unit = "";
        this.quantity = 0;
        this.isDelete = true;
    }

    public Product(int type, String nameProduct, String unit, int quantity, int price, int priceImport) {
        this.ID = generateIdProduct(type);
        this.nameProduct = nameProduct;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.priceImport = priceImport;
        this.isDelete = false;
        if (quantity <= 0 ) {
            this.isDelete = true;
        }

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



    public abstract void print();

    public String getID() {
        return ID;
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

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }



    public void setDelete(boolean flag) {
        isDelete = flag;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceImport() {
        return priceImport;
    }
}
