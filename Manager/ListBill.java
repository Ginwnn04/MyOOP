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
    private static final Date printDateUserStr = null;
    private int totalBill;
    private Bill[] bill;
    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/HoaDon.txt";


    //Constructor
    public ListBill(){
        bill = new Bill[totalBill];
    }

    public ListBill( ListBill x){
        this.bill = x.bill;
        this.totalBill=x.totalBill;
    }
    
    //Hàm tạo hóa đơn mới
    public void addBill(ListProduct listProduct, ListStaff listStaff, ListCustomer listCustomer, ListPromotionsSale listPromotionsSale){
        bill = Arrays.copyOf(bill, totalBill+1);
        bill[totalBill]=new Bill();
        bill[totalBill].input(listProduct, listStaff, listCustomer, listPromotionsSale);
        totalBill++;
    }

    //Hàm xuất hóa đơn hiện tại
    public void printBill(){
        if(totalBill==0){
            System.out.println("Chua nhap du lieu vao hoa don !");
        }
        else{
        bill[totalBill-1].print();
        }
    }

    //Hàm xuất lịch sử hóa đơn
    public void printListBill(){
        System.out.println("--------DANH SÁCH HÓA ĐƠN--------");
        for(int i=0;i<totalBill;i++){
            System.out.println("\nHóa đơn thứ "+(i+1));
            bill[i].print();
        }
    }

    //Hàm tìm kiếm hóa đơn bằng mã hóa đơn
    public void findBillByIdBill(){
        int count = 0 ;
        String idBillUser = new Validate().checkStringUser("Nhập mã hóa đơn cần tìm");
        for( int i = 0 ; i < totalBill ; i++ ){
            if((bill[i].getidBill()).equals(idBillUser)){
                bill[i].print();
                count++;
            }
        }
        if(count == 0 ){
            System.out.println("Không tìm thấy hóa đơn !");
        }
    }

    //Hàm tìm kiếm hóa đơn bằng tên nhân viên 
    public void findBillByIdEmployee(){
        int count = 0;
        String idEmployeeUser = new Validate().checkStringUser("Nhập mã nhân viên");
        for( int i = 0 ; i < totalBill ; i++ ){
            if((bill[i].getidEmployee()).equals(idEmployeeUser)){
                bill[i].print();
                count++;
            }
        }
        if(count == 0 ){
            System.out.println("Không tìm thấy hóa đơn !");
        }
    }

    //Hàm tìm kiếm hóa đơn bằng ngày xuất
   /* public void findBillByPrintDate(){
        int count = 0;
        Date printDateUser;
        boolean validDate = false;
        do {
            String printDateUserStr = new Validate().checkStringUser("Nhập ngày xuất hóa đơn (dd-MM-yyyy)");
            try {
                printDateUser = df.parse(printDateUserStr);
                validDate = true;
            } catch (ParseException e) { 
                System.out.println("Định dạng ngày không hợp lệ!");
            }
        }while (!validDate);

        for( int i = 0 ; i < totalBill ; i++ ){
            if((bill[i].getprintDate()).before(printDateUser)){
                bill[i].print();
                count++;
            }
        }
        if(count == 0 ){
            System.out.println("Không tìm thấy hóa đơn !");
        }
    }*/

    //Hàm tìm kiếm hóa đơn bằng tên khách hàng
    public void findBillByIdCustomer(){
        int count = 0;
        String idCustomer = new Validate().checkStringUser("Nhập tên khách hàng ");
        for( int i = 0 ; i < totalBill ; i++ ){
            if((bill[i].getidCustomer()).equals(idCustomer)){
                bill[i].print();
                count++;
            }
        }
        if(count == 0 ){
            System.out.println("Không tìm thấy hóa đơn !");
        }
    }
  
    //Hàm thêm sản phẩm vào hóa đơn
//    public void addProduct(){
//        bill[totalBill-1].addDetailBill();
//    }

    //Hàm xóa bớt sản phảm khỏi hóa đơn
    public void deleteProduct(){
        bill[totalBill-1].deleteDetailBill();
    }

    //Hàm thây đỏi số lượng sản phẩm 
    public void fixQuantityProduct(){
        bill[totalBill-1].fixQuantityDetailBill();
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
                if (check==true) {
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
