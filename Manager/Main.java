package MyOOP.Manager;


import MyOOP.Entity.ImportProduct;

public class Main {
    public static void main(String[] args) {
        ImportProduct test = new ImportProduct();
        test.insertProduct();
        test.printImportBill();

    }
}