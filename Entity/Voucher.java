package MyOOP.Entity;
public class Voucher {
    private String idVoucher;
    private long moneyOff;

    public Voucher(){
        this.idVoucher = "";
        this.moneyOff = 0;
    }
    public Voucher(String firstKey, long moneyOff){
        this.idVoucher = creatKey(firstKey);
        this.moneyOff = moneyOff;
    }
    public String creatKey(String firstKey) {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        int size = 12;
        StringBuilder stringBuilder = new StringBuilder(size);
        for(int i = 0; i < size; i++) {
            int x = (int)(Math.random() * 61);
            stringBuilder.append(s.charAt(x));
        }
        return firstKey + "-" + stringBuilder.toString();
    }

    public void printVoucher() {
        int colSpace = 25;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "d\n", idVoucher, moneyOff);

    }

    public String getidVoucher() {
        return idVoucher;
    }
    public long getmoneyOff(){
        return moneyOff;
    }
    public void setmoneyOff(long moneyOff){
        this.moneyOff = moneyOff;
    }
}
