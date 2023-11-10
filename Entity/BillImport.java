package MyOOP.Entity;

import MyOOP.Manager.Validate;

import java.util.Arrays;

public class BillImport {
    private String idImportProduct;
    private String importDate;
    private String idSupplier;
    private String idEmployee;
    private int totalImportProduct;
    private int totalPriceImportProduct;
    private DetailsImport[] listDetailsImport;

    public BillImport(String idSupplier, String idEmployee, String importDate) {
        this.idSupplier = idSupplier;
        this.idEmployee = idEmployee;
        this.importDate = importDate;
        idImportProduct = generateIdImportBill();
        listDetailsImport = new DetailsImport[totalImportProduct];
    }

    public BillImport(String idImportProduct, String importDate, String idSupplier, String idEmployee, int totalImportProduct, int totalPriceImportProduct, DetailsImport[] listDetailsImport) {
        this.idImportProduct = idImportProduct;
        this.importDate = importDate;
        this.idSupplier = idSupplier;
        this.idEmployee = idEmployee;
        this.totalImportProduct = totalImportProduct;
        this.totalPriceImportProduct = totalPriceImportProduct;
        this.listDetailsImport = listDetailsImport;
    }

    public BillImport(String idImportProduct, String idProduct, String nameProduct, String unit, int quantity, int importPrice, String importDate, String idEmployee, String idSupplier) {
        listDetailsImport = Arrays.copyOf(listDetailsImport, totalImportProduct + 1);
        listDetailsImport[totalImportProduct++] = new DetailsImport(idImportProduct, idProduct, nameProduct, unit, quantity, importPrice);
        totalPriceImportProduct += listDetailsImport[totalImportProduct - 1].getTotalPrice();
        this.importDate = importDate;
        this.idEmployee = idEmployee;
        this.idSupplier = idSupplier;
    }

    public BillImport() {
        idImportProduct = generateIdImportBill();
        listDetailsImport = new DetailsImport[totalImportProduct];

    }

    public void insertInfor() {
        this.idSupplier = new Validate().checkStringUser("Nhập ID nhà cung cấp");
        this.idEmployee = new Validate().checkStringUser("Nhập ID nhân viên");
        this.importDate = new Validate().checkStringUser("Nhập ngày nhập hàng");
    }

    // Nhap du lieu tu ban phim
    public void insertDetail(String idProduct, String nameProduct, String unit, int quantity, int importPrice) {
        listDetailsImport = Arrays.copyOf(listDetailsImport, totalImportProduct + 1);
        listDetailsImport[totalImportProduct++] = new DetailsImport(idImportProduct, idProduct, nameProduct, unit, quantity, importPrice);
        totalPriceImportProduct += listDetailsImport[totalImportProduct - 1].getTotalPrice();
    }



    public String generateIdImportBill() {
        return  (int)(Math.random() * 1000000000) + "";
    }


    // Chi tiet hoa don nhap san pham
    public void printImportBill() {
        String choice = new Validate().checkStringUser("Bạn có muốn in hoá đơn nhập (y/n)");
        if (choice.charAt(0) == 'n') {
            return;
        }
        System.out.println("=====================================================================================");
        System.out.printf("%" + 60 + "s", "BILL NHẬP HÀNG (" + idImportProduct + ")\n");
        System.out.printf("%" + 25 + "s %" + 25 + "s %" + 25 + "s\n", "Mã NV: " + idEmployee, "Mã NCC: " + idSupplier, "Ngày: " + importDate);
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá nhập", "Tổng tiền");
        for(DetailsImport x : listDetailsImport) {
            x.printDetail();
        }
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "d\n", "", "", "", "", "Tổng tiền", totalPriceImportProduct);
    }

    // Hien thi ra tung dong hoa don
    public void printBill() {
        int colSpace = 25;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "d %-"
                + colSpace + "d\n", idImportProduct, idSupplier, idEmployee, importDate, totalImportProduct, totalPriceImportProduct);
    }

    public String getIdImportProduct() {
        return idImportProduct;
    }


    public String printToFile() {
        String result = "";
        for (DetailsImport x : listDetailsImport) {
            result += x.printToFile() + importDate + "|" + idEmployee + "|" + idSupplier + "\n";
        }
        return result;
    }
}
