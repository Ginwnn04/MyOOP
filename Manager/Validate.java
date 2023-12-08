package DoAnOOP.Manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {
    private static Scanner sc = new Scanner(System.in);
    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public void clearBuffer() {
        sc.nextLine();
    }

    public Date inputDate(String notify, Date date){
        try {
            System.out.printf(notify + ": ");
            date = df.parse(sc.nextLine());
        } catch (Exception e) {
        }
        return date;
    }

    public String checkStringUser(String notify) {
        System.out.printf(notify + ": ");
        String inputUser = sc.nextLine();
        return inputUser;
    }
    public int checkNumberInput(String notify, String error) {
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
                // Số tiền luôn > 0, vui lòng nhập lại.
                System.out.println(error);
            }
            else {
                break;
            }
            countError++;
        }
        return inputUser;
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
