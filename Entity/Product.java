package MyOOP.Entity;

public abstract class Product {
    String ID;
    String nameProduct;
    String unit;
    int quantity;
    int price;
    boolean isDelete;

    public Product() {
        this.ID = "";
        this.nameProduct = "";
        this.price = 0;
        this.unit = "";
        this.quantity = 0;
        this.isDelete = true;
    }

    public Product(String ID, String nameProduct, String unit, int quantity, int price) {
        this.ID = ID;
        this.nameProduct = nameProduct;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.isDelete = false;
    }



    public abstract void print();
}
