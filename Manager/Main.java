package DoAnOOP.Manager;


import DoAnOOP.Entity.Drinks;
import DoAnOOP.Entity.Foods;
import DoAnOOP.Entity.Product;
import DoAnOOP.Entity.Voucher;

public class Main {
    public static void main(String[] args) {
        PromotionsSale listVoucher = new PromotionsSale("Back to school", "BTS", "22/10/2023", "1/11/2023");
        for (int i = 0; i < 2; i++) {
            listVoucher.creatVoucher();
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(listVoucher.listVoucher[i].getidVoucher() + " " + listVoucher.listVoucher[i].getmoneyOff());


        }

    }
}