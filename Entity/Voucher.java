package DoAnOOP.Entity;
import DoAnOOP.Manager.Validate;

public class Voucher {
    private String idVoucher;
    private int moneyDiscount;

    //Getter & Setter
    public String getidVoucher(){
        return idVoucher;
    }
    public void setidvoucher(String idVoucher){
        this.idVoucher=idVoucher;
    }

    public int getmoneyDiscount(){
        return moneyDiscount;
    }

    public void setmoneyDiscount(int moneyDiscount){
        this.moneyDiscount=moneyDiscount;
    }

    //Constructor
    public Voucher(){}

    public Voucher(String idVoucher, int moneyDiscount){
        this.idVoucher=idVoucher;
        this.moneyDiscount=moneyDiscount;
    }
    
    //Hàm tạo mã Voucher
    public String createIdVoucher(){
        String idVoucher="VC"+"-"+ (int)(Math.random()*1000);
        return idVoucher;
    }

    //Hàm nhập
    public void input(){
        idVoucher= createIdVoucher();
        System.out.println(idVoucher);
        moneyDiscount=new Validate().checkNumberInput("Nhập giá tiền giảm", "Số tiền > 0, vui lòng nhập lại");
        new Validate().clearBuffer();
    }

    //Hàm xuất
    public void print(){
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s\n", idVoucher, moneyDiscount);
    }

    //Ghi voucher vào file
    public String printToFile(){
        return idVoucher + "|" + moneyDiscount+"\n";
    }
}
