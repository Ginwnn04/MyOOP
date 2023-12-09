package DoAnOOP.Manager;
import DoAnOOP.Entity.*;

import java.io.*;
import java.util.Arrays;


public class ListProduct implements ServiceFile{
    private int totalProduct;
    private Product[] listProduct;
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/KhoSanPham.txt";

    public ListProduct() {
        listProduct = new Product[totalProduct];
    }
    // Copy list san pham
    public ListProduct(ListProduct x) {
        this.listProduct = x.listProduct;
        this.totalProduct = x.totalProduct;
    }

    public DetailsImport[] createProduct(String path) {
        int count = 0;
        DetailsImport[] listDetailsImport = new DetailsImport[count];
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
                    int priceProduct = new Validate().checkNumberInput("Nhập giá bán của sản phẩm " + nameProduct + " (" + priceImport + ")", "Giá bán > 0, vui lòng nhập lại" );
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
                        Product product = listProduct[totalProduct - 1];
                        listDetailsImport = Arrays.copyOf(listDetailsImport, count + 1);
                        listDetailsImport[count++] = new DetailsImport(product.getID(), product.getNameProduct(), product.getUnit(), product.getQuantity(), priceImport);
                    }
                }
            }
            System.out.println("Đã thêm thành công " + count + " sản phẩm");
            new Validate().clearBuffer();
            bufferedReader.close();
            return listDetailsImport;
        }
        catch (FileNotFoundException fnfe) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public DetailsImport createProduct() {
        System.out.println("1. Thực phẩm");
        System.out.println("2. Thức uống");
        int type = new Validate().checkNumberInput("Nhập loại sản phẩm", "Loại sản phẩm > 0, vui lòng nhập lại");
        new Validate().clearBuffer();
        String nameProduct = new Validate().checkStringUser("Nhập tên sản phẩm");
        String unit = new Validate().checkStringUser("Nhập đơn vị tính của sản phẩm");
        int quantity = new Validate().checkNumberInput("Nhập số lượng sản phẩm", "Số lượng > 0, vui lòng nhập lại");
        new Validate().clearBuffer();
        int priceImport = new Validate().checkNumberInput("Nhập giá tiền nhập sản phẩm", "Giá bán > 0, vui lòng nhập lại");
        int priceProduct = new Validate().checkNumberInput("Nhập giá tiền bán sản phẩm", "Giá nhập > 0, vui lòng nhập lại");
        if (quantity == -1 || priceProduct == -1) {
            return null;
        }
        if (type == 1) {
            new Validate().clearBuffer();
            String typeFood = new Validate().checkStringUser("Nhập loại thực phẩm");
            int amount = new Validate().checkNumberInput("Nhập khối lượng thực phẩm", "Khối lượng > 0, vui lòng nhập lại");
            if (amount != -1) {
                listProduct = Arrays.copyOf(listProduct, totalProduct + 1);
                listProduct[totalProduct++] = new Foods(type, nameProduct, unit, quantity, priceProduct, typeFood, amount, priceImport);
                return new DetailsImport(listProduct[totalProduct - 1].getID(), nameProduct, unit, quantity, priceImport);
            }
        }
            else if (type == 2){
                int volume = new Validate().checkNumberInput("Nhập thể tích", "Thể tích > 0, vui lòng nhập lại");

                listProduct = Arrays.copyOf(listProduct, totalProduct + 1);
                listProduct[totalProduct++] = new Drinks(type, nameProduct, unit, quantity, priceProduct, volume, priceImport);
                return new DetailsImport(listProduct[totalProduct - 1].getID(), nameProduct, unit, quantity, priceImport);
        }
        return null;
    }

    public void updateProduct() {
        showProduct(false);
        boolean flag = false;
        String idProductUser = new Validate().checkStringUser("Nhập vào ID sản phẩm cần sửa");
        for(int i = 0; i < totalProduct; i++) {
            if (listProduct[i].getID().equals(idProductUser)) {
                flag = true;

                //
                String idProduct = listProduct[i].getID();
                String nameProduct = new Validate().checkStringUser("Nhập tên sản phẩm");
                String unit = new Validate().checkStringUser("Nhập đơn vị tính của sản phẩm");
                int quantity = new Validate().checkNumberInput("Nhập số lượng sản phẩm", "Số lượng > 0, vui lòng nhập lại");
                new Validate().clearBuffer();
                int priceImport = new Validate().checkNumberInput("Nhập giá tiền nhập sản phẩm", "Giá bán > 0, vui lòng nhập lại");
                int priceProduct = new Validate().checkNumberInput("Nhập giá tiền bán sản phẩm", "Giá nhập > 0, vui lòng nhập lại");
                if (quantity == -1 || priceProduct == -1) {
                    System.out.printf("Bạn đã nhập sai");
                    return;
                }
                new Validate().clearBuffer();

                if (listProduct[i] instanceof Foods) {
                    String typeFood = new Validate().checkStringUser("Nhập loại thực phẩm");
                    int amount = new Validate().checkNumberInput("Nhập khối lượng thực phẩm", "Khối lượng > 0, vui lòng nhập lại");
                    if (amount != -1) {
                        listProduct[i] = new Foods(idProduct, nameProduct, unit, quantity, priceProduct, false, typeFood, amount, priceImport);
                        System.out.println("Sửa thành công");
                        return;
                    }
                }
                if (listProduct[i] instanceof Drinks) {
                    int volume = new Validate().checkNumberInput("Nhập thể tích", "Thể tích > 0, vui lòng nhập lại");
                    if (volume != -1) {
                        listProduct[i] = new Drinks(idProduct, nameProduct, unit, quantity, priceProduct, false, volume, priceImport);
                        System.out.println("Sửa thành công");
                        return;
                    }
                }
                System.out.println("Sửa thất bại");
            }
        }
        if (flag == false) {
            System.out.println("Không tìm thấy ID");
            System.out.println("Sửa thất bại");
        }
    }

    public void deleteProduct() {
        showProduct(false);
        boolean flag = false;
        String idProductUser = new Validate().checkStringUser("Nhập vào ID sản phẩm cần xoá");
        for(int i = 0; i < totalProduct; i++) {
            if (listProduct[i].getID().equals(idProductUser)) {
                flag = true;
                listProduct[i].setDelete(true);
                System.out.println("Xóa thành công");
                break;
            }
        }
        if (flag == false) {
            System.out.println("Không tìm thấy ID sản phẩm");
            System.out.println("Xóa thất thất bại");
        }
    }

    public DetailsImport[] restock() {
        int totalDetailsImport = 0;
        DetailsImport[] listDetailsImports = new DetailsImport[totalDetailsImport];
        System.out.println("1. Thêm số lượng sản phẩm ");
        System.out.println("2. Thêm số lượng sản phẩm đã hết");
        int choice = new Validate().checkChoiceUser(1, 2);
        boolean mode = true;
        if (choice == 1) {
            mode = false;
        }
        String choiceContinue = "";
        do {
            showProduct(mode);
            String idProductUser = new Validate().checkStringUser("Nhập ID sản phẩm cần thêm số lượng");
            boolean flag = false;
            for(int i = 0; i < totalProduct; i++) {
                if (listProduct[i].getID().equals(idProductUser)) {
                    int newQuantity = new Validate().checkNumberInput("Nhập số lượng sản phẩm cần thêm", "Số lượng > 0, vui lòng nhập lại");
                    if (newQuantity != -1) {
                        flag = true;
                        int quantityCurrent = listProduct[i].getQuantity();
                        listProduct[i].setQuantity(quantityCurrent + newQuantity);
                        if (listProduct[i].getIsDelete() == true) {
                            listProduct[i].setDelete(false);
                        }
                        listDetailsImports = Arrays.copyOf(listDetailsImports, totalDetailsImport + 1);
                        listDetailsImports[totalDetailsImport++] = new DetailsImport(listProduct[i].getID(), listProduct[i].getNameProduct(), listProduct[i].getUnit(), newQuantity, listProduct[i].getPriceImport());
                        System.out.println("Thêm số lượng thành công");
                        new Validate().clearBuffer();
                        break;
                    }
                }
            }
            if (flag == false) {
                System.out.println("Không tìm thấy ID sản phẩm");
                System.out.println("Thêm số lượng thất bại");
            }
            choiceContinue = new Validate().checkStringUser("Bạn có muốn tiếp tục (y/n)");

        } while(choiceContinue.charAt(0) == 'y');
        return listDetailsImports;
    }

    // Can fix lai
    public void findIdProduct(){
        boolean flag = false;
        String idProductUser = new Validate().checkStringUser("Nhập vào ID sản phẩm cần tìm");
        printFrame("SẢN PHẨM TÌM THEO ID");
        for(int i = 0 ; i < totalProduct ; i++){
            if(listProduct[i].getID().equals(idProductUser)) {
                flag = true;
                listProduct[i].print();
            }
        }
        if(flag == false){
            System.out.println("Không tìm thấy ID sản phẩm");
            System.out.println("Tìm kiếm thất bại");
        }
    }

    public void findNameProduct(){
        int colSpace = 15;
        boolean flag = false;
        String nameProduct = new Validate().checkStringUser("Nhập vào tên sản phẩm cần tìm");
        printFrame("DANH SÁCH SẢN PHẨM TÌM THEO TÊN");
        for(int i = 0 ; i < totalProduct ; i++){
            if(listProduct[i].getNameProduct().toLowerCase().contains(nameProduct.toLowerCase())){
                flag = true;
                listProduct[i].print();
            }
        }
        if(flag == false){
            System.out.println("Không tìm thấy tên sản phẩm");
            System.out.println("Tìm kiếm thất bại");
        }
    }


    public void printFrame(String title) {
        int colSpace = 15;
        System.out.println("=======================" + title + "======================");
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã sản phẩm", "Tên sản phẩm", "Khối lượng", "Thể tích","Loại thực phẩm" , "Đơn vị tính", "Số lượng", "Giá tiền");
    }



    public void showProduct(boolean isDelete) {
        printFrame("DANH SÁCH SẢN PHẨM");
        for(Product x : listProduct) {
            if (isDelete) {
                if (x.getIsDelete() == true) {
                    x.print();
                }
            }
            else {
                if (x.getIsDelete() == false) {
                    x.print();
                }
            }
        }
    }




    public void setQuantity(String idProduct, int newQuantity) {
        for(Product x : listProduct) {
            if (x.getID().equals(idProduct)) {
                x.setQuantity(newQuantity);
                if (newQuantity == 0) {
                    x.setDelete(true);
                }
            }
        }
    }

    public Product getProductByCode(String idProduct) {
        for (Product product : listProduct) {
            if (product != null && product.getID().equals(idProduct)) {
                return product;
            }
        }
        return null;
    }

    public int transPriceProduct(String idProduct) {
//        readData();
		for(int i = 0; i < totalProduct; i++) {
			if((listProduct[i].getID()).equals(idProduct)) {
				return listProduct[i].getPrice();
			}
		}
//        resetData();
        return 0;
	}

    public int transQuantityProduct(String idProduct) {
		for(int i = 0; i < totalProduct; i++) {
			if(listProduct[i].getID().indexOf(idProduct) != -1) {
				return listProduct[i].getQuantity();
			}
		}
        return 0;
	}

    public String transNameProduct(String idProduct) {
		for(int i = 0; i < totalProduct; i++) {
			if(listProduct[i].getID().indexOf(idProduct) != -1) {
				return listProduct[i].getNameProduct();
			}
		}
        return null;
	}




    @Override
    public void readData() {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                listProduct = Arrays.copyOf(listProduct, totalProduct + 1);
                String[] split = line.split("\\|");
                String idProduct = split[0];
                String nameProduct = split[1];
                String unit = split[2];
                int quantity = Integer.parseInt(split[3]);
                int priceImport = Integer.parseInt(split[4]);
                int price = Integer.parseInt(split[5]);
                boolean isDelete = Boolean.parseBoolean(split[6]);
                String firstKey = idProduct.substring(0, 2);
                if (firstKey.equals("FD")) {
                    String typeProduct = split[7];
                    int amout = Integer.parseInt(split[8]);
                    listProduct[totalProduct++] = new Foods(idProduct, nameProduct, unit, quantity, price, isDelete, typeProduct, amout, priceImport);
                }
                else {
                    int volume = Integer.parseInt(split[7]);
                    listProduct[totalProduct++] = new Drinks(idProduct, nameProduct, unit, quantity, price, isDelete, volume, priceImport);
                }
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }


    @Override
    public void writeData(boolean flag) {
        try {
            FileWriter fileWriter = new FileWriter(path, flag);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Product x : listProduct) {
                bufferedWriter.write(x.printToFile());
            }
            bufferedWriter.close();
        }
        catch (FileNotFoundException fnfe) {
        }
        catch (IOException ioe) {
        }
    }

    // Thống kê sản phẩm trong kho
    public void reportProductCurrent() {
        readData();
        int countFD = 0;
        int countDK = 0;
        for (Product p : listProduct) {
            if (p.getIsDelete() == false) {
                if (p instanceof Foods) {
                    countFD++;
                }
                if (p instanceof Drinks) {
                    countDK++;
                }
            }
        }
        System.out.format("%-15s %-15s \n", "Loại sản phẩm", "Số lượng");
        System.out.format("%-15s %-15s \n", "Thực phẩm", countFD);
        System.out.format("%-15s %-15s \n", "Đồ uống", countDK);
        String choice = new Validate().checkStringUser("Bạn có muốn xem sản phẩm trong kho không yes/no (yes để xem hoặc no từ chối)");
        if (choice.equals("yes")) {
            showProduct(false);
        }
        if (choice.equals("no")) {
            System.out.println("Đã hủy yêu cầu xem sản phẩm trong kho");
        }
    }
}