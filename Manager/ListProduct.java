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

    public void addProduct(int type) {
        // 1 la them sp tu file
        // 2 la them san pham tu ban phim
        ListBillImport listBillImport = new ListBillImport();
        BillImport billImport = new BillImport();
        if (type == 1) {
            billImport = importProductFormFile();
        }
        else if (type == 2) {
            billImport = importProduct();
        }
        if (billImport == null) {
            System.out.println("Thêm thất bại");
        }
        listBillImport.creatBillImport(billImport);
        listBillImport.writeData(true);
        writeData(true);
    }

    public BillImport importProduct() {
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
        return billImport;
    }


    public BillImport importProductFormFile() {
        BillImport billImport = new BillImport();
        billImport.insertInfor();
        String currentDirectory = System.getProperty("user.dir");
        String path = currentDirectory + "/src/DoAnOOP/NhaCungCap.txt";
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
                        count++;
                        Product product = listProduct[totalProduct - 1];
                        billImport.insertDetail(product.getID(), product.getNameProduct(), product.getUnit(), product.getQuantity(), priceImport);
                    }
                }
            }
            System.out.println("Đã thêm thành công " + count + " sản phẩm");
            new Validate().clearBuffer();
            billImport.printImportBill();
            bufferedReader.close();
            return billImport;
        }
        catch (FileNotFoundException fnfe) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public Product createProduct() {
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
            int amout = new Validate().checkNumberInput("Nhập khối lượng thực phẩm", "Khối lượng > 0, vui lòng nhập lại");
            if (amout != -1) {
                return new Foods(type, nameProduct, unit, quantity, priceProduct, typeFood, amout, priceImport);
            }
        }
        else if (type == 2){
            int volume = new Validate().checkNumberInput("Nhập thể tích", "Thể tích > 0, vui lòng nhập lại");
            return new Drinks(type, nameProduct, unit, quantity, priceProduct, volume, priceImport);
        }
        return null;
    }

    public void updateProduct() {
        showProduct(true);
        readData();
        boolean flag = false;
        String idProductUser = new Validate().checkStringUser("Nhập vào ID sản phẩm cần sửa");
        for(int i = 0; i < totalProduct; i++) {
            if (listProduct[i].getID().equals(idProductUser)) {
                flag = true;
                Product tmp =  createProduct();
                if (tmp != null) {
                    listProduct[i] = tmp;
                    System.out.println("Sửa thành công !!");
                    writeData(false);
                    return;
                }
            }
        }
        if (flag == false) {
            System.out.println("Không tìm thấy ID");
        }
        System.out.println("Sửa thất bại");

        resetData();
    }

    public void deleteProduct() {
        showProduct(true);
        readData();
        boolean flag = false;
        String idProductUser = new Validate().checkStringUser("Nhập vào ID sản phẩm cần xoá");
        for(int i = 0; i < totalProduct; i++) {
            if (listProduct[i].getID().equals(idProductUser)) {
                flag = true;
                listProduct[i].setDelete(true);
                System.out.println("Xóa thành công");
                writeData(false);
                return;
            }
        }
        if (flag == false) {
            System.out.println("Không tìm thấy ID sản phẩm");
        }
        System.out.println("Xóa thất thất bại");
        resetData();
    }

    public void restock() {
        showProduct(false);
        readData();
        String idProductUser = new Validate().checkStringUser("Nhập ID sản cần khôi phục HOẶC thêm số lượng");
        boolean flag = false;
        for(int i = 0; i < totalProduct; i++) {
            if (listProduct[i].getID().equals(idProductUser)) {
                // So luong > 0 => Can khoi phuc
                if (listProduct[i].getQuantity() > 0) {
                    listProduct[i].setDelete(false);
                    flag = true;
                    System.out.println("Khôi phục thành công!");
                    return;
                }
                // Nguoc lai => So luong = 0 => Can them so luong
                else {
                    int newQuantity = new Validate().checkNumberInput("Nhập số lượng sản phẩm cần thêm", "Số lượng > 0, vui lòng nhập lại");
                    if (newQuantity != -1) {
                        flag = true;
                        listProduct[i].setQuantity(newQuantity);
                        listProduct[i].setDelete(false);
                        System.out.println("Thêm số lượng thành công");
                        return;
                    }
                }
            }

        }
        if (flag == false) {
            System.out.println("Không tìm thấy ID sản phẩm HOẶC số lượng sản phẩm ko hợp lệ");
        }
        System.out.println("Thêm số lượng HOẶC khôi phục thất bại");
        writeData(false);
    }

    public void restock1() {
        BillImport billImport = new BillImport();
        billImport.insertInfor();
        String choice;
        do {
            showProduct(true);
            readData();
            boolean flag = true;
            String idProductUser = new Validate().checkStringUser("Nhập ID sản cần thêm số lượng");
            for(int i = 0; i < totalProduct; i++) {
                if (listProduct[i].getID().equals(idProductUser)) {
                    int newQuantity = new Validate().checkNumberInput("Nhập số lượng sản phẩm cần thêm", "Số lượng > 0, vui lòng nhập lại");
                    if (newQuantity != -1) {
                        int quantityCurrent = listProduct[i].getQuantity();
                        listProduct[i].setQuantity(quantityCurrent + newQuantity);
                        billImport.insertDetail(listProduct[i].getID(), listProduct[i].getNameProduct(), listProduct[i].getUnit(), newQuantity, listProduct[i].getPriceImport());
                        System.out.println("Thêm số lượng thành công");
                    }
                    else {
                        System.out.println("Số lượng cần thêm không hợp lệ");
                        flag = false;
                    }
                    break;
                }
            }
            if (flag == false) {
                System.out.println("Thêm số lượng thất bại");
            }
            new Validate().clearBuffer();
            writeData(false);
            resetData();
            choice = new Validate().checkStringUser("Bạn có muốn tiếp tục thêm sản phẩm không (y/n)");
        } while(choice.charAt(0) == 'y');
        billImport.printImportBill();
        ListBillImport listBillImport = new ListBillImport();
        listBillImport.creatBillImport(billImport);

    }

    // Can fix lai
    public void findIdProduct(){
        readData();
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
        resetData();


    }

    public void findNameProduct(){
        int colSpace = 15;
        readData();
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
        resetData();
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
                + colSpace + "s\n", "Mã sản phẩm", "Tên sản phẩm", "Khối lượng", "Thể tích","Loại sản phẩm" , "Đơn vị tính", "Số lượng", "Giá tiền");
    }

    public void showProduct(boolean flag) {
        readData();
        printFrame("DANH SÁCH SẢN PHẨM");
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
        resetData();
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
        readData();
		for(int i = 0; i < totalProduct; i++) {
			if((listProduct[i].getID()).equals(idProduct)) {
				return listProduct[i].getPrice();
			}
		}resetData();
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
    public void resetData() {
        totalProduct = 0;
        listProduct = new Product[totalProduct];
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
            resetData();
            bufferedWriter.close();
        }
        catch (FileNotFoundException fnfe) {
        }
        catch (IOException ioe) {
        }
    }
}