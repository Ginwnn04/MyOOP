package DoAnOOP.Entity;

import DoAnOOP.Manager.Validate;

public class Voucher {
    private String idVoucher;
    private int moneyDiscount;

    //Constructor.

    // Dành cho đọc dữ liệu từ bàn phím
    public Voucher() {
    }


    // Dành cho đọc dữ liệu từ file
    public Voucher(String idVoucher, int moneyDiscount) {
        this.idVoucher = idVoucher;
        this.moneyDiscount = moneyDiscount;
    }

    //Hàm tạo mã Voucher
    public String createIdVoucher() {
        String idVoucher = "VC" + "-" + (int) (Math.random() * 1000);
        return idVoucher;
    }

    //Hàm nhập
    public void createVoucher() {
        idVoucher = createIdVoucher();
        System.out.println(idVoucher);
        moneyDiscount = new Validate().checkNumberInput("Nhập giá tiền giảm", "Số tiền > 0, vui lòng nhập lại");
        new Validate().clearBuffer();
    }

    //Hàm xuất
    public void print() {
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s\n", idVoucher, moneyDiscount);
    }

    //Ghi voucher vào file
    public String printToFile() {
        return idVoucher + "|" + moneyDiscount + "\n";
    }

    //Getter & Setter
    public String getidVoucher() {
        return idVoucher;
    }

    public int getmoneyDiscount() {
        return moneyDiscount;
    }
}
