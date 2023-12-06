package DoAnOOP.Entity;

public class Foods extends Product{
    private String typeFood;
    private int amount;


    public Foods(int type, String nameProduct, String unit, int quantity, int price, String typeFood, int amount, int priceImport) {
        super(type, nameProduct, unit, quantity, price, priceImport);
        this.typeFood = typeFood;
        this.amount = amount;
    }

    public Foods(String idProduct, String nameProduct, String unit, int quantity, int price, boolean isDelete, String typeFood, int amount, int priceImport) {
        super(idProduct, nameProduct, unit, quantity, price, priceImport, isDelete);
        this.typeFood = typeFood;
        this.amount = amount;
    }

    @Override
    public void print() {
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "d %-"
                + colSpace + "d\n", ID, nameProduct, amount + " gram", "-" ,typeFood , unit, quantity, price);
    }

    @Override
    public String printToFile() {
        return super.printToFile() + typeFood + "|" + amount + "\n";
    }

}