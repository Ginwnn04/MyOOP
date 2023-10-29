package MyOOP.Entity;

import MyOOP.Manager.Validate;

import java.io.*;
import java.util.Arrays;

public class ImportProduct {
    private String idImportProduct;
    private String importDate;
    private String idSupplier;
    private String idEmployee;
    private int totalImportProduct;
    private long totalPriceImportProduct;
    private DetailsImport[] listDetailsImport;

    public ImportProduct(String idSupplier, String idEmployee, String importDate) {
        this.idSupplier = idSupplier;
        this.idEmployee = idEmployee;
        this.importDate = importDate;
        idImportProduct = generateIdImportBill();
        listDetailsImport = new DetailsImport[totalImportProduct];
    }

    public ImportProduct() {
        idImportProduct = generateIdImportBill();
        listDetailsImport = new DetailsImport[totalImportProduct];

    }

    public void insertInfor() {
        this.idSupplier = new Validate().checkStringUser("Nhập ID nhà cung cấp");
        this.idEmployee = new Validate().checkStringUser("Nhập ID nhân viên");
        this.importDate = new Validate().checkStringUser("Nhập ngày nhập hàng");
    }

    public void insertDetail(String idProduct, String nameProduct, String unit, int quantity, int importPrice) {
        listDetailsImport = Arrays.copyOf(listDetailsImport, totalImportProduct + 1);
        listDetailsImport[totalImportProduct++] = new DetailsImport(idImportProduct, idProduct, nameProduct, unit, quantity, importPrice);
    }

    public String generateIdImportBill() {
        return  (int)(Math.random() * 1000000000) + "";
    }

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
            totalPriceImportProduct += x.getTotalPrice();
        }
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "d\n", "", "", "", "", "Tổng tiền", totalPriceImportProduct);
    }
}
