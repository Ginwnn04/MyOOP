package MyOOP.Entity;
public class Bill {
    String idBill;
    String idEmployee;
    String idCustomer;
    String idVoucher;
    float totalBill;
    String printDate;

    public Bill (){
        this.idBill="";
        this.idEmployee="";
        this.idCustomer="";
        this.idVoucher="";
        this.totalBill=0;
        this.printDate="";
    }

    public Bill (String idBill, String idEmployee, String idCustomer, String idVoucher, float totalBill, String printDate){
        this.idBill=idBill;
        this.idEmployee=idEmployee;
        this.idCustomer=idCustomer;
        this.idVoucher=idVoucher;
        this.totalBill=totalBill;
        this.printDate=printDate;
    }

    public String getidBill(){
        return idBill;
    }
    public void setidBill(String idBill){
        this.idBill=idBill;
    }

    public String getidEmployee(){
        return idEmployee;
    }
    public void setidEmployee(String idEmployee){
        this.idEmployee=idEmployee;
    }

    public String getidCustomer(){
        return idCustomer;
    }
    public void setidCustomer(String idCustomer){
        this.idCustomer=idCustomer;
    }

    public String getidVoucher() {
        return idVoucher;
    }
    public void setidVoucher(String idVoucher) {
        this.idVoucher=idVoucher;
    }

    public float gettotalBill(){
        return totalBill;
    }
    public void settotalBill(float totalBill){
        this.totalBill=totalBill;
    }

    public String getprintDate(){
        return idBill;
    }
    public void setprintDate(String printDate){
        this.printDate=printDate;
    }

}
