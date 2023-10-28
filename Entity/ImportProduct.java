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

    public ImportProduct(String importDate) {
        this.importDate = importDate;
        idImportProduct = generateIdImportBill();
        listDetailsImport = new DetailsImport[totalImportProduct];
    }

    public boolean insertProduct() {

        String pathSupplier = System.getProperty("user.dir") + "\\src\\MyOOP\\NhaCungCap.txt";

        try {
            FileReader fileReader = new FileReader(pathSupplier);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("\\|");
                int type = Integer.parseInt(split[0]);
                if (type == 1 || type == 2) {
                    String nameProduct = split[1];
                    String unit = split[2];
                    int quantity = Integer.parseInt(split[3]);
                    long priceImport = Long.parseLong(split[4]);
                    listDetailsImport = Arrays.copyOf(listDetailsImport, totalImportProduct + 1);
                    listDetailsImport[totalImportProduct++] = new DetailsImport(type, idImportProduct, nameProduct, unit, quantity, priceImport);
                }
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException fnfe) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public boolean insertProductFormKeyboard() {
        System.out.println("1. Thực phẩm");
        System.out.println("2. Thức uống");
        String type = new Validate().checkStringUser("Nhập loại sản phẩm");
        String nameProduct = new Validate().checkStringUser("Nhập tên sản phẩm");
//        new Validate().clearBuffer();
        String unit = new Validate().checkStringUser("Nhập đơn vị tính của sản phẩm");
        long quantity = new Validate().checkNumberProduct("Nhập số lượng sản phẩm");
        long priceProduct = new Validate().checkNumberProduct("Nhập số tiền nhập sản phẩm");
        if (quantity == -1 || priceProduct == -1) {
            return false;
        }
        listDetailsImport = Arrays.copyOf(listDetailsImport, totalImportProduct + 1);
        listDetailsImport[totalImportProduct++] = new DetailsImport(Integer.parseInt(type), generateIdImportBill(), nameProduct, unit, (int)quantity, priceProduct);
        return true;
    }

    public boolean updateWareHouse() {
        // Kiem tra nhung sp nao co so luong = 0 thi an? di
        for(DetailsImport x : listDetailsImport) {
            if (x.getQuantity() == 0) {
                x.setIsDelete(true);
            }
        }

        String pathWareHouse = System.getProperty("user.dir") + "\\src\\MyOOP\\Kho.txt";
        try {
            FileWriter fileWriter = new FileWriter(pathWareHouse);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(DetailsImport x : listDetailsImport) {
                if (x.getIsDelete() == false) {
                    bufferedWriter.write(x.getIdProduct() + "|" + x.getNameProduct() + "|" + x.getUnit() + "|" + x.getQuantity() + "|" + x.getImportPrice() + "\n");
                }
            }
            bufferedWriter.close();
        }
        catch (FileNotFoundException fnfe) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public String generateIdImportBill() {
        return  (int)(Math.random() * 1000000000) + "";
    }

    public void printImportBill() {
        String idEmployee = "1234567890";
        System.out.printf("%" + 60 + "s", "BILL NHẬP HÀNG (" + idImportProduct + ")\n");
        System.out.printf("%" + 30 + "s %" + 30 + "s\n", "Mã NV: " + idEmployee, "Ngày: " + importDate);
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
