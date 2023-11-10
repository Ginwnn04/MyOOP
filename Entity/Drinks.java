package MyOOP.Entity;

public class Drinks extends Product{
    private int volume;

    public Drinks() {
        super();
    }

    public Drinks(int type, String nameProduct, String unit, int quantity, int price,  int volume) {
        super(type, nameProduct, unit, quantity, price);
        this.volume = volume;
    }

    public Drinks(String ID, String nameProduct, String unit, int quantity, int price, int volume) {
        super(ID, nameProduct, unit, quantity, price);
        this.volume = volume;
    }

    @Override
    public void print() {
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "d %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "d %-"
                + colSpace + "d\n", ID, nameProduct, "-", volume ,"-" , unit, quantity, price);
    }

    @Override
    public String printToFile() {
        return super.printToFile() + volume;
    }

}
