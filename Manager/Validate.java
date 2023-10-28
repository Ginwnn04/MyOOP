package MyOOP.Manager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {
    private static Scanner sc = new Scanner(System.in);

    public void clearBuffer() {
        sc.nextLine();
    }


    public String checkStringUser(String notify) {
        System.out.printf(notify + ": ");
        String inputUser = sc.nextLine();
        return inputUser;
    }
    public int checkMoneyInput(String notify) {
        System.out.printf(notify + ": ");
        int inputUser = -1;
        int countError = 0;
        while (countError < 3 ) {
            try {
                inputUser = sc.nextInt();
            }
            catch(InputMismatchException ime) {
                System.out.println("Nhập sai định dạng, vui lòng nhập lại.");
            }
            if (inputUser < 0) {
                System.out.println("Số tiền luôn > 0, vui lòng nhập lại.");
            }
            else {
                break;
            }
            countError++;
        }
        return inputUser;
    }


    // Check so tien nhap vao va so luong
    public int checkNumberProduct(String notify) {
        System.out.printf(notify + ": ");
        int cntError = 0;
        while (cntError < 3) {
            try {
                int numberUser = sc.nextInt();
                if (numberUser > 0) {
                    return numberUser;
                }
            }
            catch (InputMismatchException ime) {

            }
            cntError++;
        }
        return -1;
    }

    public int checkChoiceUser(int min, int max) {
        while(true) {
            System.out.printf("Nhập vào lựa chọn của bạn: ");
            try {
                int choice = sc.nextInt();
                if (choice >= min && choice <= max) {
                    sc.nextLine();
                    System.out.println("");
                    return choice;
                }
                else {
                    System.out.println("Vui lòng nhập lại trong khoảng " + "[" + min + "," + max + "]");
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Nhập sai định dạng, vui lòng nhập lựa chọn bằng SỐ");
                sc.nextLine();
                System.out.println("");
                return -1;
            }
        }
    }


}
