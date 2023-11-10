package MyOOP.Manager;

import MyOOP.Entity.BillImport;
import MyOOP.Entity.Product;
import MyOOP.Entity.ServiceFile;

import java.io.*;
import java.util.Arrays;

public class ListBillImport implements ServiceFile {
    private BillImport[] listBill;
    private int totalBill;
    private String path = System.getProperty("user.dir") + "/src/MyOOP/PhieuNhap.txt";

    public ListBillImport() {
        listBill = new BillImport[totalBill];
    }

    public ListBillImport(ListBillImport list) {
        this.listBill = list.listBill;
        this.totalBill = list.totalBill;
    }

    public void creatBillImport(BillImport billImport) {
        listBill = Arrays.copyOf(listBill, totalBill + 1);
        listBill[totalBill++] = billImport;
    }

    public void show() {
        int colSpace = 25;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã phiếu nhập", "Mã nhà cung cấp", "Mã NV giám sát", "Ngày nhập", "Số lượng sp nhập", "Tổng tiền");
        for (BillImport x : listBill) {
            x.printBill();
        }
    }


    @Override
    public void readData() {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                listBill = Arrays.copyOf(listBill, totalBill + 1);
                String[] split = line.split("\\|");
                String idBillImport = split[0];
                String idProduct = split[1];
                String nameProduct = split[2];
                String unit = split[3];
                int quantity = Integer.parseInt(split[4]);
                int priceImport = Integer.parseInt(split[5]);
                String importDate = split[6];
                String idEmployee = split[7];
                String idSupplier = split[8];
                BillImport bill = new BillImport(idBillImport, idProduct, nameProduct, unit, quantity, priceImport, importDate , idEmployee, idSupplier);
                creatBillImport(bill);
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }

    @Override
    public void writeData() {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(BillImport x : listBill) {
                bufferedWriter.write(x.printToFile());
            }
            listBill = null;
            totalBill = 0;
            bufferedWriter.close();
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }
}
