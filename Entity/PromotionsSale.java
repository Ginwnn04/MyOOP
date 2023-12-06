package DoAnOOP.Entity;
import DoAnOOP.Manager.Validate;

import java.util.Arrays;

public class PromotionsSale {
    private String namePromotions;
    private String idPromotions;
    private String startDate;
    private String endDate;
    private int totalVoucher=0;
    private Voucher[] voucher= new Voucher[totalVoucher];

    //Constructor
    public PromotionsSale() {
    }

    public PromotionsSale(String namePromotions, String idPromotions, String startDate, String endDate) {
        this.namePromotions = namePromotions;
        this.idPromotions = idPromotions;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getter & Setter
    public String getnamePromotions(){
        return namePromotions;
    }

    public void setnamePromotions(String namePromotions){
        this.namePromotions=namePromotions;
    }

    public String getidPromotions(){
        return idPromotions;
    }

    public void setidPromotions(String idPromotions){
        this.idPromotions=idPromotions;
    }

    public String getstartDate(){
        return startDate;
    }

    public void setstartDate(String startDate){
        this.startDate=startDate;
    }

    public String getendDate(){
        return endDate;
    }

    public void setendDate(String endDate){
        this.endDate=endDate;
    }

    //Hàm tạo mã CTKM
    public String createIdPromotions(){
        String idPromotions="KM"+"-"+ (int)(Math.random()*10000);
        return idPromotions;
    }

    //Hàm nhập
    public void input(){
        idPromotions = createIdPromotions();
        namePromotions = new Validate().checkStringUser("Nhập tên chương trình khuyến mãi");
        startDate = new Validate().checkStringUser("Nhập ngày bắt đầu");
        endDate = new Validate().checkStringUser("Nhập kết thúc");
        totalVoucher = new Validate().checkNumberInput("Số voucher cần tạo", "Số voucher > 0, vui long nhập lại !");
        voucher = new Voucher[totalVoucher];
            for(int i = 0 ; i < totalVoucher ; i++ ){
            voucher[i]= new Voucher();
            voucher[i].input();
        }
    }

    //Hàm xuat
    public void print(){
        int colSpace = 15;
//        System.out.println("\n\n=================================================");
//        System.out.println("\t KHUYẾN MÃI "+namePromotions+" ["+idPromotions+"] ");
//        System.out.println("BẮT ĐẦU TỪ "+ startDate + " KẾT THÚC VÀO "+ endDate );
//        System.out.println("-------------------------------------------------");
//        System.out.printf("\t%-" + colSpace + "s %-"
//                + colSpace + "s\n", "Mã giảm giá", "Tiền giảm");
//        System.out.println("-------------------------------------------------");
        for(Voucher x : voucher){
            System.out.printf("%-" + colSpace + "s %-"
                    + colSpace + "s %-" + colSpace + "s %-" + colSpace + "s ", idPromotions, namePromotions, startDate, endDate);
                x.print();
        }
    }

    //Hàm thêm Voucher
    public void addVoucher(){
        int quantityVoucher = new Validate().checkNumberInput("So voucher muon them", "So phai > 0, vui long nhap lai !");
        for(int i = 0 ; i < quantityVoucher ; i++) {
        voucher = Arrays.copyOf(voucher, totalVoucher+1);
                voucher[totalVoucher]=new Voucher();
                voucher[totalVoucher].input();
                totalVoucher++;
        }
        System.out.println("Thêm voucher thành công !");
    }

    //Hàm xóa Voucher
    public void deleteVoucher(){
        int count = 0;
        String idVoucherUser=new Validate().checkStringUser("Nhập mã voucher cần xóa của CTKM "+namePromotions);
        for(int i=0;i<totalVoucher;i++){
            if((voucher[i].getidVoucher()).equals(idVoucherUser)){
                totalVoucher--;
               for(int j=i; j<totalVoucher; j++){
			   voucher[j] = voucher[j+1];
               }
               count++;
               System.out.println("Xóa voucher thành công !");
            }
        }
        if(count==0){
            System.out.println("Không tìm thấy voucher !");
        }
    }

    //Hàm tìm kiếm Voucher bằng mã
    public void findIdVoucher(){
        int count=0;
        String idVoucherUser=new Validate().checkStringUser("\nNhập mã voucher cần tìm");
        for(int i=0;i<totalVoucher;i++){
            if((voucher[i].getidVoucher()).equals(idVoucherUser)){
                voucher[i].print();
                count++;
            }
        }
        if(count==0){
            System.out.println("Không tìm thấy voucher !");
        }
    }

    //Hàm lấy giá trị tiền giảm
    public int TransVoucher(String idVoucher) {
		for(int i = 0; i < totalVoucher; i++) {
			if(idVoucher.equals((voucher[i].getidVoucher()))) {
				return voucher[i].getmoneyDiscount();
			}
		}
        return 0;
	}

    //Hàm thêm Voucher bằng truyền tham số
    public void insertVoucher(String idVoucher, int moneyDiscount) {
        voucher = Arrays.copyOf(voucher, totalVoucher + 1);
        voucher[totalVoucher] = new Voucher(idVoucher,moneyDiscount);
        totalVoucher ++;
    }

    //Ghi CTKM vào file
    public String printToFile() {
        String result = "";
        for (Voucher x : voucher) {
            result += namePromotions + "|" + idPromotions + "|" + startDate + "|" + endDate + "|" + x.printToFile();
        }
        return result;
    }

}
