package DoAnOOP.Entity;
import DoAnOOP.Manager.ListProduct;


public class DetailBill {
    ListProduct lisProduct = new ListProduct();

    private String idProduct;
    private String nameProduct;
    private int price;
    private int quantity;
    private int total;

    //Constructor
    public DetailBill(){
        this.idProduct="";
        this.nameProduct="";
        this.price=0;
        this.quantity=0;
        this.total=0;
    }

        public DetailBill(String nameProduct,String idProduct,int price, int quantity){
            this.idProduct=idProduct;
            this.nameProduct=nameProduct;
            this.price=price;
            this.quantity=quantity;
            this.total=price*quantity;
        }

    //Getter & Setter
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

   public String getnameProduct(){
        return nameProduct;
    }

    public void setnameProduct(String nameProduct){
        this.nameProduct=nameProduct;
    }

    public int getprice(){
        return price;
    }

    public void setprice(int price){
        this.price=price;
    }

    public int gettotal(){
        return total;
    }

    public void settotal(int total){
        this.total=total;
    }

    //Hàm xuất
    public void print(){
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
        + colSpace + "s %-"
                    + colSpace + "s %-"
                    + colSpace + "s %-"
                    + colSpace + "s\n", idProduct, nameProduct, price ,quantity, total);
    }

    //Ghi chi tiết hóa đơn vào File
    public String printToFile() {
        return nameProduct + "|"+ idProduct + "|" + price + "|" + quantity ;
    }
}
