package DoAnOOP.Entity;

import DoAnOOP.Manager.ListProduct;
import DoAnOOP.Manager.ListStaff;
import DoAnOOP.Manager.ListSupplier;
import DoAnOOP.Manager.Validate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BillImport {
    private String idImportProduct;
    private Date importDate;
    private String idSupplier;
    private String idEmployee;
    private int totalImportProduct;
    private int totalPriceImportProduct;
    private DetailsImport[] listDetailsImport;
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/NhaCungCap.txt";
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public BillImport() {
        idImportProduct = generateIdImportBill();
        listDetailsImport = new DetailsImport[totalImportProduct];
    }

    public BillImport(String idImportProduct, String idSupplier, String idEmployee, Date importDate) {
        this.idImportProduct = idImportProduct;
        this.idSupplier = idSupplier;
        this.idEmployee = idEmployee;
        this.importDate = importDate;
        listDetailsImport = new DetailsImport[totalImportProduct];
    }



    public void createBillImport(ListProduct listProduct, ListStaff listStaff, ListSupplier listSupplier, boolean flag) {
        importDate = new Date();

        do {
            idSupplier = new Validate().checkStringUser("Nhập ID nhà cung cấp");
            if (!listSupplier.checkExists(idSupplier)) {
                System.out.println("ID nhà cung cấp không tồn tại. Vui lòng nhập lại");
            }
        } while(!listSupplier.checkExists(idSupplier));
        do {
            idEmployee = new Validate().checkStringUser("Nhập ID nhân viên giám sát");
            if (!listStaff.checkExists(idEmployee)) {
                System.out.println("ID nhân viên giám sát không tồn tại. Vui lòng nhập lại");
            }
        } while(!listStaff.checkExists(idEmployee));

        if (flag) {
            // Hoi nhap tu file hay ban phim
            String choice = new Validate().checkStringUser("Nhập dữ liệu từ bản phím (y/n)");
            if (choice.charAt(0) == 'y') {
                do {
                    listDetailsImport = Arrays.copyOf(listDetailsImport, totalImportProduct + 1);
                    listDetailsImport[totalImportProduct++] = listProduct.createProduct();
                    new Validate().clearBuffer();
                    choice = new Validate().checkStringUser("Tiếp tục thêm sản phẩm (y/n)");
                } while (choice.charAt(0) == 'y');
            }
            // nguoc lai thi nhap du lieu tu file
            // luc nay hay vi them tung chi tiet hoa don vao thi ta gan nguyen danh sach chi tiet hoa don
            else {
                listDetailsImport = listProduct.createProduct(path);
                totalImportProduct = listDetailsImport.length;
            }
        }
        else {
            listDetailsImport = listProduct.restock();
            totalImportProduct = listDetailsImport.length;
        }
        totalPriceImportProduct = sumMoney();
        printImportBill();


    }


    // Nhap du lieu tu ban phim
    public void insertDetail(String idProduct, String nameProduct, String unit, int quantity, int importPrice) {
        listDetailsImport = Arrays.copyOf(listDetailsImport, totalImportProduct + 1);
        listDetailsImport[totalImportProduct++] = new DetailsImport(idImportProduct, idProduct, nameProduct, unit, quantity, importPrice);
        totalPriceImportProduct += listDetailsImport[totalImportProduct - 1].getTotalPrice();
    }

    public int sumMoney() {
        int totalMoney = 0;
        for(DetailsImport x : listDetailsImport) {
            totalMoney += x.getTotalPrice();
        }
        return totalMoney;
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
        System.out.printf("%" + 25 + "s %" + 25 + "s %" + 25 + "s\n", "Mã NV: " + idEmployee, "Mã NCC: " + idSupplier, "Ngày: " + df.format(importDate));
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá nhập", "Thành tiền");
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
                + colSpace + "d\n", idImportProduct, idSupplier, idEmployee, df.format(importDate), totalImportProduct, totalPriceImportProduct);
    }

    public String getIdImportProduct() {
        return idImportProduct;
    }


    public String printToFile() {
        String result = "";
        for (DetailsImport x : listDetailsImport) {
            result += x.printToFile() + df.format(importDate) + "|" + idEmployee + "|" + idSupplier + "\n";
        }
        return result;
    }


}
