package DoAnOOP.Entity;

import DoAnOOP.Manager.Validate;

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

    public ImportProduct() {
        listDetailsImport = new DetailsImport[totalImportProduct];
    }

    public boolean insertProduct() {
        String path = new Validate().checkStringUser("Nhập đường dẫn file");
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("\\|");
                int type = Integer.parseInt(split[0]);
                if (type == 1 || type == 2) {
                    idImportProduct = generateIdImportBill();
                    String nameProduct = split[1];
                    String unit = split[2];
                    int quantity = Integer.parseInt(split[3]);
                    long priceImport = Long.parseLong(split[4]);
                    listDetailsImport = Arrays.copyOf(listDetailsImport, totalImportProduct + 1);
                    listDetailsImport[totalImportProduct++] = new DetailsImport(type, idImportProduct, nameProduct, unit, quantity, priceImport);
                }
            }
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
        String nameEmployee = "Nguyễn Nhật Quang";
        System.out.printf("\t\t\t\t Bill nhập hàng\n");
        System.out.println("\t\t\t\t " + idImportProduct);
        System.out.printf("%-" + 25 + "s %-"
                + 25 + "s\n", nameEmployee, "25/10/2023");
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
