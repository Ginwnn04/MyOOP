package DoAnOOP.Entity;

import DoAnOOP.Manager.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class Bill {

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private String idBill;
    private String idEmployee;
    private String idCustomer;
    private Date printDate;
    private int totalBill;
    private int moneyDiscount;
    private int totalPay;
    private int totalDetailBill = 0;
    private DetailBill[] detailBill = new DetailBill[totalDetailBill];

    //Constructor
    public Bill() {
    }

    public Bill(String idBill, Date printDate, String idEmployee, String idCustomer, int totalBill, int moneyDiscount) {
        this.idBill = idBill;
        this.idEmployee = idEmployee;
        this.idCustomer = idCustomer;
        this.printDate = printDate;
        this.totalBill = totalBill;
        this.moneyDiscount = moneyDiscount;
        this.totalPay = totalBill - moneyDiscount;
    }

    //Ham tao ma hoa don
    public String createIdBill() {
        String firtID = "HD";
        return firtID + "-" + (int) (Math.random() * 10000000);
    }

    //Hàm nhập
    public void createBill(ListProduct listProduct, ListStaff listStaff, ListCustomer listCustomer, ListPromotionsSale listPromotionsSale) {
        printDate = new Date();
        idBill = createIdBill();
        idEmployee = new Validate().checkStringUser("Nhập mã nhân viên");
        if (!listStaff.checkExists(idEmployee)) {
            System.out.println("Mã nhân viên ko tồn tại");
            return;
        }
        //Nhập chi tiết hóa đơn
        System.out.println("Nhập chi tiết hóa đơn.");
        String choice = "";
        do {
            addDetailBill(listProduct);
            new Validate().clearBuffer();
            //Lựa chọn tiếp tục mua thêm hoặc thanh toán
            choice = new Validate().checkStringUser("Bạn có muôn mua thêm (y/n)");
        } while (choice.charAt(0) == 'y');

        //Lựa chọn sử dụng có sử dụng voucher hay không
        choice = new Validate().checkStringUser("Bạn có mã giảm giá không (y/n)");
        if (choice.charAt(0) == 'y') {

            String idPromotions = new Validate().checkStringUser("Nhập vào mã CTKM");
            String idVoucher = new Validate().checkStringUser("Nhap ma voucher");
            moneyDiscount = listPromotionsSale.transMoneyDiscount(idPromotions, idVoucher, printDate);
            if (moneyDiscount == 0) {
                System.out.println("Mã giảm giá bạn vừa nhập không hợp lệ !!!");
            }
        }
        totalPay = totalBill - moneyDiscount;
        //Thong tin khach hang
        choice = new Validate().checkStringUser("Có phải là thanh viên (y/n)");
        if (choice.charAt(0) == 'y') {
            String phone = new Validate().checkStringUser("Nhập số điện thoại khách hàng (+84)");
            listCustomer.readData();
            idCustomer = listCustomer.transIdCustomer(phone);
            if (idCustomer.equals("")) {
                System.out.println("Không tồn tại khách hàng có sdt là " + phone + "\n");
            }
        } else {
            choice = new Validate().checkStringUser("Có muốn trở thành thành viên không (y/n)");
            if (choice.charAt(0) == 'y') {
                idCustomer = listCustomer.createCustomer();
            }
        }
        printDetailsBill();
    }

    //Hàm xuất chi tiết hoá đơn
    public void printDetailsBill() {
        int colSpace = 15;
        System.out.println("=====================================================================================");
        System.out.printf("%" + 55 + "s", "HOÁ ĐƠN BÁN HÀNG (" + idBill + ")\n");
        System.out.printf("%" + 30 + "s %" + 30 + "s\n", "Mã NV: " + idEmployee, "Mã khách hàng: " + idCustomer);
        System.out.printf("%" + 60 + "s", "Ngày in hoá đơn: " + df.format(printDate) + "\n");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng", "Thành tiền");
        for (DetailBill x : detailBill) {
            x.print();
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Tổng tiền : " + totalBill);
        System.out.println("Tiền giảm : " + moneyDiscount);
        System.out.println("Tiền cần phải trả : " + totalPay);
    }

    // Xuất tổng quan hoá đơn
    public void printOverviewBill() {
        int colSpace = 25;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", idBill, idEmployee, idCustomer, totalDetailBill, totalPay);
    }

    //Hàm mua thêm sản phẩm vào hóa đơn
    public void addDetailBill(ListProduct listProduct) {
        String idProduct;
        int quantity;
//        listProduct.readData();
        detailBill = Arrays.copyOf(detailBill, totalDetailBill + 1);

        //Nhập mã sản phẩm và kiểm tra với từng mã sản phẩm trong kho
        do {
            listProduct.showProduct(false);
            System.out.println("Chi tiết thứ " + (totalDetailBill + 1));
            idProduct = new Validate().checkStringUser("Nhap ma san pham");
            if (listProduct.transPriceProduct(idProduct) == 0)
                System.err.println("\nMã san pham mà bạn vừa nhập không hợp lệ hoặc không có trong danh sách!!!");
        } while (listProduct.transPriceProduct(idProduct) == 0);

        //Lấy giá trị giá tiền,tên sản phẩm,số lượng sản phẩm tương ứng
        int price = listProduct.transPriceProduct(idProduct);
        String nameProduct = listProduct.transNameProduct(idProduct);
        int quantityCheck = listProduct.transQuantityProduct(idProduct);

        //Nhập số lượng sản phẩm cần mua
        do {
            quantity = new Validate().checkNumberInput("Nhập số lượng sản phẩm", "Số lượng sản phẩm > 0, vui lòng nhập lại !");
            if (quantity > quantityCheck) {
                System.out.println("Sản phẩm trong kho không đủ !");
            }
        } while (quantity > quantityCheck);
        listProduct.setQuantity(idProduct, (quantityCheck - quantity));
        detailBill[totalDetailBill] = new DetailBill(nameProduct, idProduct, price, quantity);
        totalDetailBill++;

        totalBill += detailBill[totalDetailBill - 1].getTotal();
        totalPay = totalBill - moneyDiscount;

    }

    //Lưu sản phẩm từ File
    public void insertDetailBill(String nameProduct, String idProduct, int quantity, int price) {
        detailBill = Arrays.copyOf(detailBill, totalDetailBill + 1);
        detailBill[totalDetailBill] = new DetailBill(nameProduct, idProduct, quantity, price);
        totalDetailBill++;
    }

    //Ghi hóa đơn vào File
    public String printToFile() {
        String result = "";
        for (DetailBill x : detailBill) {
            result += idBill + "|" + df.format(printDate) + "|" + idEmployee + "|" + idCustomer + "|" + x.printToFile() + "|" + totalBill + "|" + moneyDiscount + "|" + totalPay + "\n";
        }
        return result;
    }

    //Getter & Setter
    public String getidBill() {
        return idBill;
    }
    public String getidCustomer() {
        return idCustomer;
    }
}