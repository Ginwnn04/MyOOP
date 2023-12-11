package DoAnOOP.Manager;
import DoAnOOP.Entity.ServiceFile;
import DoAnOOP.Entity.Bill;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ListBill implements ServiceFile {
    private int totalBill;
    private Bill[] bill;
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/HoaDon.txt";


    //Constructor
    public ListBill(){
        bill = new Bill[totalBill];
    }

    //Hàm tạo hóa đơn mới
    public void addBill(ListProduct listProduct, ListStaff listStaff, ListCustomer listCustomer, ListPromotionsSale listPromotionsSale){
        bill = Arrays.copyOf(bill, totalBill+1);
        bill[totalBill]=new Bill();
        bill[totalBill].createBill(listProduct, listStaff, listCustomer, listPromotionsSale);
        totalBill++;
    }

    // Xuất tổng quan danh sách hoá đơn
    public void printListOverviewBill() {
        System.out.println("=================================DANH SÁCH HÓA ĐƠN=====================================");
        int colSpace = 25;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã phiếu bán hàng", "Mã nhân viên", "Mã khách hàng", "Số lượng", "Tổng tiền");
        for(int i = 0; i < totalBill; i++){
            bill[i].printOverviewBill();
        }
    }

    //Hàm tìm kiếm hóa đơn bằng mã hóa đơn
    public void findBillByIdBill(){
        int count = 0 ;
        String idBillUser = new Validate().checkStringUser("Nhập mã hóa đơn cần tìm");
        for( int i = 0 ; i < totalBill ; i++ ){
            if((bill[i].getidBill()).equals(idBillUser)){
                bill[i].printDetailsBill();
                count++;
            }
        }
        if(count == 0 ){
            System.out.println("Không tìm thấy hóa đơn !");
        }
    }


    //Hàm tìm kiếm hóa đơn bằng tên khách hàng
    public void findBillByIdCustomer(){
        int count = 0;
        String idCustomer = new Validate().checkStringUser("Nhập mã khách hàng ");
        for( int i = 0 ; i < totalBill ; i++ ){
            if((bill[i].getidCustomer()).equals(idCustomer)){
                bill[i].printDetailsBill();
                count++;
            }
        }
        if(count == 0 ){
            System.out.println("Không tìm thấy hóa đơn !");
        }
    }

    @Override
    public void writeData(boolean flag) {
        try {
            FileWriter fileWriter = new FileWriter(path, flag);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for( Bill x : bill){
                bufferedWriter.write(x.printToFile());
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void readData() {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            boolean check = true;
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                String[] split = line.split("\\|");
                String idBillFile = split[0];
                Date printDateFile = df.parse(split[1]);
                String idEmployeeFile = split[2];
                String idCustomerFile = split[3]; 
                String nameProductFile = split[4];
                String idProductFile = split[5];
                int priceFile = Integer.parseInt(split[6]);
                int quantityFile = Integer.parseInt(split[7]);
                int totalBillFile = Integer.parseInt(split[8]);
                int moneyDiscountFile = Integer.parseInt(split[9]);
                if (check == true) {
                    bill = Arrays.copyOf(bill, totalBill + 1);
                    bill[totalBill] = new Bill(idBillFile,printDateFile,idEmployeeFile,idCustomerFile,totalBillFile,moneyDiscountFile);
                    bill[totalBill].insertDetailBill(nameProductFile, idProductFile, quantityFile, priceFile);
                    totalBill++;
                    check = false;
                }
                else{  
                    if (idBillFile.equals(bill[totalBill-1].getidBill())) {
                        bill[totalBill-1].insertDetailBill(nameProductFile, idProductFile, quantityFile, priceFile);
                    }
                    else {
                        bill = Arrays.copyOf(bill, totalBill + 1);
                        bill[totalBill] = new Bill(idBillFile,printDateFile,idEmployeeFile,idCustomerFile,totalBillFile,moneyDiscountFile);
                        bill[totalBill].insertDetailBill(nameProductFile, idProductFile, quantityFile, priceFile);
                        totalBill++;
                    }
                }
            }
            bufferedReader.close();
        }
        catch (Exception e) {
        }
    }
}
