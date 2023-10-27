package MyOOP.Manager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {
    private static Scanner sc = new Scanner(System.in);

    public String checkStringUser(String notify) {
        System.out.printf(notify + ": ");
        String inputUser = sc.next();
        return inputUser;
    }
    public long checkMoneyInput(String notify) {
        System.out.printf(notify + ": ");
        long inputUser = -1;
        int countError = 0;
        while (countError < 3 ) {
            try {
                inputUser = sc.nextLong();
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

}
