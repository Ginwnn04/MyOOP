package DoAnOOP.Manager;

import DoAnOOP.Entity.*;

import java.io.*;
import java.util.Arrays;

public class ListCustomer implements ServiceFile {
    private Customer[] listCustomer;
    private int totalCustomer;
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/DanhSachKhachHang.txt";

    public ListCustomer() {
        listCustomer = new Customer[totalCustomer];
    }

    public ListCustomer(ListCustomer list) {
        this.listCustomer = list.listCustomer;
        this.totalCustomer = list.totalCustomer;
    }


    // ádasd
    public String createCustomer() {
        String fullName = new Validate().checkStringUser("Nhập tên khách hàng");
        String phone = new Validate().checkStringUser("Nhập sđt khách hàng");
        listCustomer = Arrays.copyOf(listCustomer, totalCustomer + 1);
        listCustomer[totalCustomer++] = new Customer(fullName, phone);
        String idCustomer = listCustomer[totalCustomer - 1].getidCustomer();
        return idCustomer;
    }

    public void printCustomer() {
        int colSpace = 15;
        System.out.println("=======================DANH SÁCH KHÁCH HÀNG======================");
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã khách hàng", "Họ khách hàng", "Tên khách hàng", "Số điện thoại");
        for (Customer x : listCustomer) {
            x.show();
        }
    }

    public void insertCustomer(String idCustomer, String lastName, String firstName, String phone) {
        listCustomer = Arrays.copyOf(listCustomer, totalCustomer + 1);
        listCustomer[totalCustomer] = new Customer(idCustomer,lastName,firstName,phone);
        totalCustomer ++;
    }

    public String transIdCustomer(String phone) {
		for(int i = 0; i < totalCustomer; i++) {
			if((listCustomer[i].getphone()).equals(phone)) {
				return listCustomer[i].getidCustomer();
			}
		}
        return "";
	}

    @Override
    public void writeData(boolean flag) {
        try {
            FileWriter fileWriter = new FileWriter(path, flag);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Customer x : listCustomer) {
                bufferedWriter.write(x.printToFile());
            }
            bufferedWriter.close();
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }



    @Override
    public void readData() {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                listCustomer = Arrays.copyOf(listCustomer, totalCustomer + 1);
                String[] split = line.split("\\|");
                String idProduct = split[0];
                String lastName = split[1];
                String firstName = split[2];
                String phone = split[3];
                listCustomer[totalCustomer++] = new Customer(idProduct, lastName, firstName, phone);
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }

}
