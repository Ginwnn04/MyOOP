package MyOOP.Manager;

import MyOOP.Entity.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ListProduct {
    private int totalProduct;
    private Product[] listProduct;

    public ListProduct() {
        listProduct = new Product[totalProduct];
    }

    public boolean importProduct(int typeInput) {
        String date = new Validate().checkStringUser("Nhập ngày nhập sản phẩm");
        ImportProduct importProduct = new  ImportProduct(date);
        if (typeInput == 1) {
            importProduct.insertProduct();
        }
        else if(typeInput == 2) {
            String choice;
            do {
                importProduct.insertProductFormKeyboard();
                new Validate().clearBuffer();
                choice = new Validate().checkStringUser("Bạn có muốn thêm tiếp sản phẩm (y/n)");
            } while(choice.charAt(0) == 'y');
        }
        importProduct.updateWareHouse();
        String path = System.getProperty("user.dir") + "\\src\\MyOOP\\Kho.txt";
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("\\|");
                String idProduct = split[0];
                String nameProduct = split[1];
                String unit = split[2];
                int quantity = Integer.parseInt(split[3]);
                int price = Integer.parseInt(split[4]);
                listProduct = Arrays.copyOf(listProduct, totalProduct + 1);
                String type = idProduct.substring(0,2);
                System.out.println("Nhập thông tin sản phẩm thứ " + (totalProduct + 1));
                if (type.equals("FD")) {
                    // Food
                    String typeFood = new Validate().checkStringUser("Nhập loại thực phẩm");
                    int amout = new Validate().checkNumberProduct("Nhập vào khối lượng");
                    listProduct[totalProduct++] = new Foods(idProduct, nameProduct, unit, quantity, price, typeFood, amout);
                }
                else {

                    int volume = new Validate().checkNumberProduct("Nhập vào thể tích");
                    listProduct[totalProduct++] = new Drinks(idProduct, nameProduct, unit, quantity, price, volume);
                }
                new Validate().clearBuffer();
            }
            bufferedReader.close();
            new Validate().clearBuffer();
            String choice = new Validate().checkStringUser("Bạn có muốn in hoá đơn nhập không (y/n)");
            if (choice.charAt(0) == 'y') {
                importProduct.printImportBill();

            }

            return true;
        }
        catch (FileNotFoundException fnfe) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public void displayProduct(int type) {
        String title = "";
        if (type == 1) {
            title = "DANH SÁCH THỰC PHẨM";
        }
        else if(type == 2) {
            title = "DANH SÁCH THỨC UỐNG";
        }
        int colSpace = 15;
        System.out.printf("%" + 40 + "s\n", title);
        System.out.printf("%-" + colSpace + "s %-"
                            + colSpace + "s %-"
                            + colSpace + "s %-"
                            + colSpace + "s %-"
                            + colSpace + "s", "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá");
    }

    public void show() {
        String title = "DANH SÁCH SẢN PHẨM";
        int colSpace = 15;
        System.out.printf("%" + 60 + "s\n", title);
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã sản phẩm", "Tên sản phẩm", "Khối lượng", "Thể tích","Loại sản phẩm" , "Đơn vị tính", "Số lượng", "Giá tiền");
        for(Product x : listProduct) {
            x.print();
        }
    }

}
