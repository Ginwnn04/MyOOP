package MyOOP.Entity;


import MyOOP.Manager.Validate;

import java.util.Arrays;

public class PromotionsSale {
    private String namePromotions;
    private String keyPromotions;
    private String startDate;
    private String endDate;
    private int totalVoucher;
    public Voucher[] listVoucher;


    public PromotionsSale() {
        this.listVoucher = new Voucher[totalVoucher];
    }

    public PromotionsSale(String namePromotions, String keyPromotions, String startDate, String endDate) {
        this.listVoucher = new Voucher[totalVoucher];
        this.namePromotions = namePromotions;
        this.keyPromotions = keyPromotions;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String creatVoucher() {
        long discount = new Validate().checkMoneyInput("Nhập vào số tiền giảm giá");
        if (discount == -1) {
            return null;
        }
        listVoucher = Arrays.copyOf(listVoucher, totalVoucher + 1);
        listVoucher[totalVoucher++] = new Voucher(keyPromotions, discount);
        new Validate().clearBuffer();
        return listVoucher[totalVoucher - 1].getidVoucher();
    }

    public void showPromotionsSale() {
        int colSpace = 25;
        for(Voucher x : listVoucher) {
            System.out.printf("%-" + colSpace + "s %-"
                    + colSpace + "s %-"
                    + colSpace + "s %-"
                    + colSpace + "s %-"
                    + colSpace + "s\n", namePromotions, startDate, endDate, x.getidVoucher(), x.getmoneyOff());
        }

    }

    public void showVoucher() {
        int colSpace = 25;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s\n", "Mã voucher", "Tiền giảm");

        for(Voucher x: listVoucher) {
            x.printVoucher();
        }
    }


    public String getKeyPromotions() {
        return keyPromotions;
    }
}
