package MyOOP.Manager;

import java.util.Arrays;

public class ListPromotionsSale {
    public PromotionsSale[] listPromotionsSale;
    public int totalPromotionsSale;

    public ListPromotionsSale() {
        listPromotionsSale = new PromotionsSale[totalPromotionsSale];
    }

    public ListPromotionsSale(ListPromotionsSale x) {
        this.listPromotionsSale = x.listPromotionsSale;
        this.totalPromotionsSale = x.totalPromotionsSale;
    }

    public void addPromotionsSale() {
        String namePromotions = new Validate().checkStringUser("Nhập tên chương trình khuyến mãi");
        String keyPromotions = new Validate().checkStringUser("Kí tự bắt đầu khuyến mãi");
        String startDate = new Validate().checkStringUser("Nhập ngày bắt đầu chương trình khuyến mãi");
        String endDate = new Validate().checkStringUser("Nhập ngày kết thúc chương trình khuyến mãi");
        listPromotionsSale = Arrays.copyOf(listPromotionsSale, totalPromotionsSale + 1);
        listPromotionsSale[totalPromotionsSale++] = new PromotionsSale(namePromotions, keyPromotions, startDate, endDate);
        System.out.println("Tạo chương trình khuyến mãi thành công !");
    }

    public void showAllPromotionsSale() {
        int colSpace = 25;
        System.out.println("=======================DANH SÁCH CHƯƠNG TRÌNH KHUYẾN MÃI======================");
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Tên chương trình", "Ngày bắt đầu", "Ngày kết thúc", "Mã voucher", "Tiền giảm giá");
        for(PromotionsSale x : listPromotionsSale) {
            x.showPromotionsSale();

        }
    }

    public void addVoucher() {
        String choice = new Validate().checkStringUser("Bạn có muốn thêm voucher");
        if (choice.charAt(0) == 'y') {
            String keyPromotionsSale = new Validate().checkStringUser("Nhập mã chương trình khuyến mãi");
            for(int i = 0;i < totalPromotionsSale; i++) {
                if (listPromotionsSale[i].getKeyPromotions().equals(keyPromotionsSale)) {
                    do {
                        listPromotionsSale[i].creatVoucher();
                        System.out.println("Tạo mã thành công !");
                        choice = new Validate().checkStringUser("Bạn có muốn tiếp tục (y/n)");
                    } while (choice.charAt(0) == 'y');
                }
            }
        }
    }
}
