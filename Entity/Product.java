package MyOOP.Entity;

public abstract class Product {
    String ID;
    String nameProduct;
    String unit;
    int quantity;
    String importDate;
    String productDate;
    int price;
    boolean isDelete;

    public Product() {
        this.ID = "";
        this.nameProduct = "";
        this.price = 0;
        this.unit = "";
        this.quantity = 0;
        this.importDate = "";
        this.productDate = "";
        this.isDelete = true;
    }

    public Product(int type, String nameProduct, String unit, int quantity, String importDate, String productDate, int price) {
        this.ID = generateIdProduct(type);
        this.nameProduct = nameProduct;
        this.unit = unit;
        this.quantity = quantity;
        this.importDate = importDate;
        this.productDate = productDate;
        this.price = price;
        this.isDelete = false;
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
}
