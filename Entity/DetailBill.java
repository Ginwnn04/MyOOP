package DoAnOOP.Entity;

import DoAnOOP.Manager.ListProduct;


public class DetailBill {
    private String idProduct;
    private String nameProduct;
    private int price;
    private int quantity;
    private int total;

    //Constructor
    public DetailBill() {
        this.idProduct = "";
        this.nameProduct = "";
        this.price = 0;
        this.quantity = 0;
        this.total = 0;
    }

    public DetailBill(String nameProduct, String idProduct, int price, int quantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
    }


    //Hàm xuất.
    public void print() {
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", idProduct, nameProduct, price, quantity, total);
    }

    //Ghi chi tiết hóa đơn vào File
    public String printToFile() {
        return nameProduct + "|" + idProduct + "|" + price + "|" + quantity;
    }

    //Getter & Setter
    public int getTotal() {
        return total;
    }
}
