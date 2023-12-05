package DoAnOOP.Manager;

import DoAnOOP.Entity.BillImport;
import DoAnOOP.Entity.Product;
import DoAnOOP.Entity.ServiceFile;

import java.io.*;
import java.util.Arrays;

public class ListBillImport implements ServiceFile {
    private BillImport[] listBill;
    private int totalBill;
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/PhieuNhap.txt";

    public ListBillImport() {
        listBill = new BillImport[totalBill];
    }

    public ListBillImport(ListBillImport list) {
        this.listBill = list.listBill;
        this.totalBill = list.totalBill;
    }



    public void creatBillImport(BillImport billImport) {
        if (listBill == null) {
            System.out.println("Tao dang null");
        }
        listBill = Arrays.copyOf(listBill, totalBill + 1);
        listBill[totalBill++] = billImport;
        writeData(true);
    }

    public void show() {
        readData();
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
        resetData();
    }


    @Override
    public void readData() {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            int total = 0;
            boolean check = true;
            BillImport[] billImport = new BillImport[0];
            while ((line = bufferedReader.readLine()) != null) {
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
                if (check) {
                    billImport = Arrays.copyOf(billImport, total + 1);
                    billImport[total++] = new BillImport(idBillImport, idEmployee, idSupplier, importDate);
                    check = false;
                }
                if (idBillImport.equals(billImport[total - 1].getIdImportProduct())) {
                    billImport[total - 1].insertDetail(idProduct, nameProduct, unit, quantity, priceImport);
                }
                else {
                    billImport = Arrays.copyOf(billImport, total + 1);
                    billImport[total++] = new BillImport(idBillImport, idEmployee, idSupplier, importDate);
                    billImport[total - 1].insertDetail(idProduct, nameProduct, unit, quantity, priceImport);
                }

            }
            listBill = billImport;
            totalBill = total;
            billImport = null;
            total = 0;
            bufferedReader.close();
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }

    @Override
    public void resetData() {
        totalBill = 0;
        listBill = new BillImport[totalBill];
    }

    @Override
    public void writeData(boolean flag) {
        try {
            FileWriter fileWriter = new FileWriter(path, flag);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(BillImport x : listBill) {
                bufferedWriter.write(x.printToFile());
            }
            resetData();
            bufferedWriter.close();
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }
}