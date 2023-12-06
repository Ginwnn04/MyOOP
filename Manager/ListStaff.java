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
        System.out.println("=======================DANH SÁCH NHÂN VIÊN======================");
        System.out.format("%-15s %-15s %-15s %-15s \n", "Mã Nhân Viên", "Họ Nhân Viên", "Tên Nhân Viên", "Số điện thoại");
        for (Staff staff : listStaff) {
            if(staff!=null)
            staff.showStaff();
        }
    }

    public Staff findStaff(String idFix) {
        for (Staff staffCurrent : listStaff) {
            if (staffCurrent != null && staffCurrent.getIdStaff().equals(idFix)) {
                return staffCurrent;
            }
        }
        return null;
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
    public void fixData() {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.out.println("=====================THAY ĐỔI THÔNG TIN NHÂN VIÊN========================");
            String idToFix = new Validate().checkStringUser("Nhập ID nhân viên cần thay đổi");
            Staff fixStaff = findStaff(idToFix);
            if (fixStaff != null) {
                System.out.println("Thông tin nhân viên cần sửa:");
                System.out.format("%-15s %-15s %-15s %-15s \n", "Mã Nhân Viên", "Họ Nhân Viên", "Tên Nhân Viên", "Số điện thoại");
                fixStaff.showStaff();
                System.out.println("Thông tin nhân viên muốn thay đổi là:");
                System.out.println("1. Họ và tên");
                System.out.println("2. Số điện thoại");
                System.out.println("3. Cả 2");
                int x = new Validate().checkChoiceUser(1, 3);
                switch (x) {
                    case 1:
                        String newName = new Validate().checkStringUser("Nhập họ và tên mới");
                        fixStaff.setFullName(newName);
                        break;
                    case 2:
                        String newPhone = new Validate().checkStringUser("Nhập số điện thoại mới");
                        fixStaff.setPhone(newPhone);
                        break;
                    case 3:
                        String Name = new Validate().checkStringUser("Nhập họ và tên mới");
                        String Phone = new Validate().checkStringUser("Nhập số điện thoại mới");
                        fixStaff.setFullName(Name);
                        fixStaff.setPhone(Phone);
                        break;
                }
                for (int i = 0; i < totalStaff; i++) {
                    if (listStaff[i].getIdStaff().equals(fixStaff.getIdStaff())) {
                        bufferedWriter.write(listStaff[i].printToFile());
                    } else {
                        bufferedWriter.write(listStaff[i].printToFile());
                    }
                }
                System.out.println("Đã thay đổi thông tin thành công");
            } else {
                System.out.println("Không tìm thấy nhân viên có mã " + idToFix);
                for (int i = 0; i < totalStaff; i++) {
                    bufferedWriter.write(listStaff[i].printToFile());
                }
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteData() {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.out.println("=====================XÓA NHÂN VIÊN========================");
            String idDelete = new Validate().checkStringUser("Nhập mã nhân viên cần xóa");
            Staff deleteStaff = findStaff(idDelete);
            if (deleteStaff != null) {
                for (int i = 0; i < totalStaff; i++) {
                    if (!listStaff[i].getIdStaff().equals(deleteStaff.getIdStaff())) {
                        bufferedWriter.write(listStaff[i].printToFile());
                    }
                }
                System.out.println("Đã xóa thành công nhân viên có mã " + idDelete);
            } else {
                for (int i = 0; i < totalStaff; i++) {
                    bufferedWriter.write(listStaff[i].printToFile());
                }
                System.out.println("Không tìm thấy nhân viên có mã " + idDelete);
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (FileNotFoundException fnfe) {

        } catch (IOException ioe) {

        }
    }
    @Override
    public void resetData(){
        totalStaff = 0;
        listStaff = new Staff[totalStaff];
    }
}
