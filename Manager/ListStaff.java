package DoAnOOP.Manager;
import DoAnOOP.Entity.*;

import java.io.*;
import java.util.Arrays;

public class ListStaff implements ServiceFile {
    private Staff[] listStaff;
    private int totalStaff;
    private String path = System.getProperty("user.dir") + "/src/DoAnOOP/DanhSachNhanVien.txt";

    public ListStaff() {
        this.listStaff = new Staff[totalStaff];
    }

    public void createStaff() {
        String fullName = new Validate().checkStringUser("Nhập họ và tên nhân viên");
        String phone = new Validate().checkStringUser("Nhập số điện thoại nhân viên");
        listStaff = Arrays.copyOf(listStaff, totalStaff + 1);
        listStaff[totalStaff++] = new Staff(fullName, phone);
    }

    public void printStaff() {
        int colSpace = 15;
        System.out.println("=======================DANH SÁCH NHÂN VIÊN======================");
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s\n", "Mã Nhân Viên", "Họ Nhân Viên", "Tên Nhân Viên", "Số điện thoại");
        for (Staff staff : listStaff) {
            if(staff!=null)
            staff.showStaff();
        }
    }

    public boolean checkExists(String idEmployee) {
        for(Staff x : listStaff) {
            if (x.getIdStaff().equals(idEmployee)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void writeData(boolean flag) {
        try {
            FileWriter fileWriter = new FileWriter(path, flag);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Staff staff : listStaff) {
                bufferedWriter.write(staff.printToFile());
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (FileNotFoundException fnfe) {

        } catch (IOException ioe) {

        }
    }
    @Override
    public void readData(){
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                listStaff = Arrays.copyOf(listStaff, totalStaff + 1);
                String[] split = line.split("\\|");
                String idStaff = split[0];
                String lastName = split[1];
                String firstName = split[2];
                String phone = split[3];
                listStaff[totalStaff++] = new Staff(idStaff,lastName,firstName,phone);
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (FileNotFoundException fnfe){

        }
        catch (Exception e){

        }
    }

}
