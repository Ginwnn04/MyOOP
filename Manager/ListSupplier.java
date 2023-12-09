package DoAnOOP.Manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import DoAnOOP.Entity.ServiceFile;
import DoAnOOP.Entity.Supplier;

public class ListSupplier implements ServiceFile {
    private Supplier[] listSuppliers;
    private int totalSupplier;
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/DanhSachNhaCungCap.txt";

    public ListSupplier() {
        listSuppliers = new Supplier[totalSupplier];
    }

    public ListSupplier(ListSupplier list) {
        this.listSuppliers = list.listSuppliers;
        this.totalSupplier = list.totalSupplier;
    }

    public void createSupplier() {
        String nameSupplier = new Validate().checkStringUser("Nhập tên nhà cung cấp");
        String address = new Validate().checkStringUser("Nhập địa chỉ nhà cung cấp");
        String phone = new Validate().checkStringUser("Nhập số điện thoại nhà cung cấp");
        listSuppliers = Arrays.copyOf(listSuppliers, totalSupplier + 1);
        listSuppliers[totalSupplier++] = new Supplier(nameSupplier, address, phone);
    }

    public boolean checkExists(String idSupplier) {
        for(Supplier x : listSuppliers) {
            if (x.getIdSupplier().equals(idSupplier)) {
                return true;
            }
        }
        return false;
    }

    public void printSupplier() {
        int colSpace = 15;
        System.out.println("=======================DANH SÁCH NHÀ CUNG CẤP======================");
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã nhà cung cấp", "Họ tên nhà cung cấp", "Địa chỉ nhà cung cấp", "Số điện thoại nhà cung cấp");
        for (Supplier x : listSuppliers) {
            x.show();
        }
    }

    @Override
    public void writeData(boolean flag) {
        try {
            FileWriter fileWriter = new FileWriter(path, flag);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Supplier x : listSuppliers) {
                bufferedWriter.write(x.prinToFile());
            }
            bufferedWriter.close();
        } catch (FileNotFoundException fnfe) {

        } catch (IOException ioe) {

        }
    }

    @Override
    public void readData() {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                listSuppliers = Arrays.copyOf(listSuppliers, totalSupplier + 1);
                String[] split = line.split("\\|");
                String idSuppkier = split[0];
                String fullname = split[1];
                String address = split[2];
                String phone = split[3];
                listSuppliers[totalSupplier++] = new Supplier(idSuppkier, fullname, address, phone);
            }
            bufferedReader.close();
        } catch (FileNotFoundException fnfe) {

        } catch (IOException iose) {

        }
    }


}