package MyOOP.Entity;

public class Foods extends Product{
    private String typeFood;
    private float amount;

    public Foods() {
        super();
    }

    public Foods(String idProduct, String nameProduct, String unit, int quantity, String importDate, String productDate, int price, String typeFood, float amount) {
        super(idProduct, nameProduct, unit, quantity, importDate, productDate, price);
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
                + colSpace + "d %-"
                + colSpace + "s %-"
                + colSpace + "d\n", ID, nameProduct, amount + " gram" ,typeFood , unit, quantity, productDate, price);
    }
}
