package MyOOP.Manager;

import MyOOP.Entity.BillImport;

import java.util.Arrays;

public class ListBillImport {
    BillImport[] listBill;
    int size;

    public ListBillImport() {
        listBill = new BillImport[size];
    }

    public ListBillImport(ListBillImport list) {
        this.listBill = list.listBill;
        this.size = list.size;
    }

    public void creatBillImport(BillImport billImport) {
        listBill = Arrays.copyOf(listBill, size + 1);
        listBill[size++] = billImport;
    }

    public void show() {
        int colSpace = 15;
        System.out.printf("%-" + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "s %-"
                + colSpace + "d %-"
                + colSpace + "d\n", "Mã phiếu nhập", "Mã nhà cung cấp", "Mã NV giám sát", "Ngày nhập", "Số lượng sp nhập", "Tổng tiền");
        for (BillImport x : listBill) {
            x.printBill();
        }
    // Ma bill, ngay nhap, nhan vien, so luong, tong tien
    }
}
