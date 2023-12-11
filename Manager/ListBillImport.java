package DoAnOOP.Manager;

import DoAnOOP.Entity.BillImport;
import DoAnOOP.Entity.Product;
import DoAnOOP.Entity.ServiceFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ListBillImport implements ServiceFile {
    private BillImport[] listBill;
    private int totalBill = 0;
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/PhieuNhap.txt";
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public ListBillImport() {
        listBill = new BillImport[totalBill];
    }


    // Them bill khi them san pham moi
    public void addBillImport(ListProduct listProduct, ListStaff listStaff, ListSupplier listSupplier, boolean flag) {
        listBill = Arrays.copyOf(listBill, totalBill + 1);
        listBill[totalBill] = new BillImport();
        listBill[totalBill++].createBillImport(listProduct, listStaff, listSupplier, flag);
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
                Date importDate = new Date();
                try {
                    importDate = df.parse(split[6]);
                }
                catch (ParseException pe) {

                }
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
    public void writeData(boolean flag) {
        try {
            FileWriter fileWriter = new FileWriter(path, flag);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(BillImport x : listBill) {
                bufferedWriter.write(x.printToFile());
            }
            bufferedWriter.close();
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }
}