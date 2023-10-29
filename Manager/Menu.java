package MyOOP.Manager;

import MyOOP.Entity.ImportProduct;

public class Menu {
    ListProduct list = new ListProduct();
    public void printMenuEmployee() {
        int choice;
        do {
            System.out.println("==================QUẢN LÝ CỬA HÀNG=====================");
            System.out.println("1. Thêm sản phẩm.");
            System.out.println("2. Hiển thị danh sách sản phẩm.");
            System.out.println("3. Sửa thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Thống kê.");
            System.out.println("6. Chương trình khuyến mãi");
            System.out.println("7. Đăng xuất.");
            choice = new Validate().checkChoiceUser(1,7);
            switch (choice) {
                case 1:
                    subMenuAdd();
                    break;
                case 2:
                    list.show();
                    break;
            }
        } while(choice != 7);
    }

    public void subMenuAdd() {

        int choice;
        do {
            System.out.println("=====================THÊM SẢN PHẨM========================");
            System.out.println("1. Thêm sản phẩm từ file.");
            System.out.println("2. Thêm sản phẩm từ bàn phím.");
            System.out.println("3. Quay lại.");
            choice = new Validate().checkChoiceUser(1,3);
            switch (choice) {
                case 1:

                    list.importProductFormFile();
                    break;
                case 2:
                    list.importProduct();
                    break;
            }

        } while(choice != 3);
    }
}
