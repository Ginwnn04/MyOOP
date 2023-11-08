package MyOOP.Manager;

import MyOOP.Entity.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class ListProduct {
    private Product[] listProduct;
    private int totalProduct;
    public ListBillImport listBillImport;

    public ListProduct() {
        listProduct = new Product[totalProduct];
        listBillImport = new ListBillImport();
    }
    // Copy list san pham
    public ListProduct(ListProduct x) {
        this.listProduct = x.listProduct;
        this.totalProduct = x.totalProduct;
        this.listBillImport = x.listBillImport;
    }

    public void importProduct() {
        BillImport billImport = new BillImport();
        billImport.insertInfor();
        String choice = "";
        do {
            System.out.println("====================THÊM SẢN PHẨM======================");
            Product product = createProduct();
            if (product != null) {
                listProduct = Arrays.copyOf(listProduct, totalProduct + 1);
                listProduct[totalProduct++] = product;
                billImport.insertDetail(product.getID(), product.getNameProduct(), product.getUnit(), product.getQuantity(), product.getPriceImport());
                System.out.println("Thêm thành công !");
            }
            else {
                System.out.printf("Thêm thất bại !");
            }
            new Validate().clearBuffer();
            choice = new Validate().checkStringUser("Bạn có muốn tiếp tục thêm sản phẩm không (y/n)");

        } while (choice.charAt(0) == 'y');
        billImport.printImportBill();
        listBillImport.creatBillImport(billImport);

    }

    public void importProductFormFile() {
        BillImport billImport = new BillImport();
        billImport.insertInfor();
        String path = new Validate().checkStringUser("Nhập vào địa chỉ file");
        int count = 0;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("\\|");
                int type = Integer.parseInt(split[0]);
                if (type == 1 || type == 2) {
                    String nameProduct = split[1];
                    String unit = split[2];
                    int quantity = Integer.parseInt(split[3]);
                    int priceImport = Integer.parseInt(split[4]);
                    listProduct = Arrays.copyOf(listProduct, totalProduct + 1);
                    int priceProduct = new Validate().checkNumberProduct("Nhập giá bán của sản phẩm " + nameProduct + " (" + priceImport + ")" );
                    if (priceProduct != -1) {
                        if (type == 1) {
                            String typeFood = split[5];
                            int amout = Integer.parseInt(split[6]);
                            listProduct[totalProduct++] = new Foods(type, nameProduct, unit, quantity, priceProduct, typeFood, amout, priceImport);
                        }
                        else {
                            int volume = Integer.parseInt(split[5]);
                            listProduct[totalProduct++] = new Drinks(type, nameProduct, unit, quantity, priceProduct, volume, priceImport);
                        }
                        count++;
                        Product product = listProduct[totalProduct - 1];
                        billImport.insertDetail(product.getID(), product.getNameProduct(), product.getUnit(), product.getQuantity(), product.getPriceImport());
                    }
                }
            }
            System.out.println("Đã thêm thành công " + count + " sản phẩm");
            new Validate().clearBuffer();
            billImport.printImportBill();
            listBillImport.creatBillImport(billImport);
            bufferedReader.close();

        }
        catch (FileNotFoundException fnfe) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    public Product createProduct() {
        System.out.println("1. Thực phẩm");
        System.out.println("2. Thức uống");
        int type = new Validate().checkNumberProduct("Nhập loại sản phẩm");
        new Validate().clearBuffer();
        String nameProduct = new Validate().checkStringUser("Nhập tên sản phẩm");
        String unit = new Validate().checkStringUser("Nhập đơn vị tính của sản phẩm");
        int quantity = new Validate().checkNumberProduct("Nhập số lượng sản phẩm");
        new Validate().clearBuffer();
        int priceImport = new Validate().checkNumberProduct("Nhập giá tiền nhập sản phẩm");
        int priceProduct = new Validate().checkNumberProduct("Nhập giá tiền bán sản phẩm");
        if (quantity == -1 || priceProduct == -1) {
            return null;
        }
        if (type == 1) {
            new Validate().clearBuffer();
            String typeFood = new Validate().checkStringUser("Nhập loại thực phẩm");
            int amout = new Validate().checkNumberProduct("Nhập khối lượng thực phẩm");
            if (amout != -1) {
                return new Foods(type, nameProduct, unit, quantity, priceProduct, typeFood, amout, priceImport);
            }
        }
        else if (type == 2){
            int volume = new Validate().checkNumberProduct("Nhập thể tích");
            return new Drinks(type, nameProduct, unit, quantity, priceProduct, volume, priceImport);
        }
        return null;
    }

    public void updateProduct() {
        showProduct(true);
        boolean flag = false;
        String idProductUser = new Validate().checkStringUser("Nhập vào ID sản phẩm cần sửa");
        for(int i = 0; i < totalProduct; i++) {
            if (listProduct[i].getID().equals(idProductUser)) {
                flag = true;
                Product tmp =  createProduct();
                if (tmp != null) {
                    listProduct[i] = tmp;
                    System.out.println("Sửa thành công !!");
                    return;
                }
            }
        }
        if (flag == false) {
            System.out.println("Không tìm thấy ID");
        }
        System.out.println("Sửa thất bại");

    }

    public void deleteProduct() {
        showProduct(true);
        boolean flag = false;
        String idProductUser = new Validate().checkStringUser("Nhập vào ID sản phẩm cần xoá");
        for(int i = 0; i < totalProduct; i++) {
            if (listProduct[i].getID().equals(idProductUser)) {
                flag = true;
                listProduct[i].setDelete(true);
                System.out.println("Xóa thành công");
                return;
            }
        }
        if (flag == false) {
            System.out.println("Không tìm thấy ID sản phẩm");
        }
        System.out.println("Xóa thất thất bại");

    }

    public void addQuantityProduct() {
        showProduct(false);
        String idProductUser = new Validate().checkStringUser("Nhập ID sản phẩm cần thêm số lượng");
        boolean flag = false;
        for(int i = 0; i < totalProduct; i++) {
            if (listProduct[i].getID().equals(idProductUser)) {
                int newQuantity = new Validate().checkNumberProduct("Nhập số lượng sản phẩm cần thêm");
                if (newQuantity != -1) {
                    listProduct[i].setQuantity(newQuantity);
                    flag = true;
                    listProduct[i].setDelete(false);
                    System.out.println("Thêm số lượng thành công");
                    return;
                }
            }
        }
        if (flag == false) {
            System.out.println("Không tìm thấy ID sản phẩm");
        }
        System.out.println("Thêm số lượng thất thất bại");
    }

    public void report() {

    }



    public void showProduct(boolean flag) {
        int colSpace = 15;
        System.out.println("=======================DANH SÁCH SẢN PHẨM======================");
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã sản phẩm", "Tên sản phẩm", "Khối lượng", "Thể tích","Loại sản phẩm" , "Đơn vị tính", "Số lượng", "Giá tiền");
        for(Product x : listProduct) {
            if (flag) {
                if (x.getIsDelete() == false) {
                    x.print();
                }
            }
            else {
                if (x.getIsDelete() == true) {
                    x.print();
                }
            }
        }
    }



}