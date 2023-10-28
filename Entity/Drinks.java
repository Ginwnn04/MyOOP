package DoAnOOP.Entity;

public class Drinks extends Product{
    private int volume;

    public Drinks() {
        super();
    }

    public Drinks(String idProduct, String nameProduct, String unit, int quantity, String importDate, String productDate, int price,  int volume) {
        super(idProduct, nameProduct, unit, quantity, importDate, productDate, price);
        this.volume = volume;
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
                + colSpace + "d\n", ID, nameProduct, volume + " ml" , unit, quantity, productDate, price);
    }


}
