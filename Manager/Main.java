package MyOOP.Manager;


public class Main {
    public static void main(String[] args) {
        PromotionsSale promotionsSale = new PromotionsSale("Back to school", "BTS", "22/10/2023", "1/11/2023");
        for (int i = 0; i < 2; i++) {
            //System.out.println(promotionsSale.creatVoucher());
            promotionsSale.creatVoucher();
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(promotionsSale.listVoucher[i].getidVoucher() + " " + promotionsSale.listVoucher[i].getmoneyOff());
        }
    }
}