package DoAnOOP.Entity;
public class Detailbill {
    String idBill;
    String idProduct;
    int quantity;
    int price;
    float total;

    public Detailbill(){
        this.idProduct="";
        this.quantity=0;
        this.total=0;
    }

    public Detailbill(String idProduct, int quantity, float total){
        this.idProduct=idProduct;
        this.quantity=quantity;
        this.total=total;
    }

    public String getidBill(){
        return idBill;
    }
    public void setidBill(String idBill){
        this.idBill=idBill;
    }

    public String getidProduct(){
        return idProduct;
    }
    public void setidProduct(String idProduct){
        this.idProduct=idProduct;
    }

    public int getquantity(){
        return quantity;
    }
    public void setquantity(int quantity){
        this.quantity=quantity;
    }

    public int getprice(){
        return price;
    }
    public void setprice(int price){
        this.price=price;
    }

    public float gettotal(){
        return total;
    }
    public void settotal(float tolal){
        this.total=tolal;
    }
}
