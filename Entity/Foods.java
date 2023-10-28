package MyOOP.Entity;

public class Foods extends Product{
    private String typeFood;
    private int amount;

    public Foods() {
        super();
    }

    public Foods(String idProduct, String nameProduct, String unit, int quantity, int price, String typeFood, int amount) {
        super(idProduct, nameProduct, unit, quantity, price);
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
}
