package MyOOP.Manager;

public class Menu {
    ListProduct list = new ListProduct();
    ListPromotionsSale listSale = new ListPromotionsSale();
    public void printMenuEmployee() {
        int choice;
        do {
            System.out.println("==================QUẢN LÝ CỬA HÀNG=====================");
            System.out.println("1. Thêm sản phẩm.");
            System.out.println("2. Hiển thị danh sách sản phẩm.");
            System.out.println("3. Sửa thông tin sản phẩm");
            System.out.println("4. Tìm kiếm tin sản phẩm");
            System.out.println("5. Xoá sản phẩm");
            System.out.println("6. Thống kê.");
            System.out.println("7. Chương trình khuyến mãi");
            System.out.println("8. Đăng xuất.");
            choice = new Validate().checkChoiceUser(1, 8);
            switch (choice) {
                case 1:
                    addNewProductMenu();
                    break;
                case 2:
                    list.showProduct(true);
                    break;
                case 3:
                    list.updateProduct();
                    break;
                case 4:
                    // Find
                    break;
                case 5:
                    list.deleteProduct();
                    break;
                case 6:
                    // Report
                    break;
                case 7:
                    promotionsSaleMenu();
                    break;

            }
        } while(choice != 7);
    }

    public void subMenuAdd() {
        int choice;
        do {
            System.out.println("=====================THÊM MỚI SẢN PHẨM========================");
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

    public void addNewProductMenu() {
        int choice;
        do {
            System.out.println("=====================THÊM SẢN PHẨM========================");
            System.out.println("1. Thêm mới sản phẩm.");
            System.out.println("2. Thêm số lượng sản phẩm.");
            System.out.println("3. Quay lại.");
            choice = new Validate().checkChoiceUser(1,3);
            switch (choice) {
                case 1:
                    subMenuAdd();
                    break;
                case 2:
                    list.addQuantityProduct();
                    break;
            }

        } while(choice != 3);
    }

    public void promotionsSaleMenu() {
        int choice;
        do {
            System.out.println("=====================TẠO CHƯƠNG TRÌNH KHUYẾN MÃI========================");
            System.out.println("1. Tạo chương trình khuyến mãi.");
            System.out.println("2. Hiển thị danh sách mã khuyến mãi của 1 chương trình.");
            System.out.println("3. Quay lại.");
            choice = new Validate().checkChoiceUser(1,3);
            switch (choice) {
                case 1:
                    listSale.addPromotionsSale();
                    break;
                case 2:
                    listSale.showAllPromotionsSale();
                    listSale.addVoucher();
                    break;
            }

        } while(choice != 3);
    }

    public void reportMenu() {
        int choice;
        do {
            System.out.println("=====================THỐNG KÊ========================");
            System.out.println("1. Thống kê số lượng sản phẩm đang tồn.");
            System.out.println("2. Thống kê số lượng sản phẩm đã bán.");
            System.out.println("3. Thống kê số lượng mã khuyến mãi của từng chương trình.");
            System.out.println("4. Thống kê lợi nhuận.");
            System.out.println("5. Quay lại.");
            choice = new Validate().checkChoiceUser(1,5);
            switch (choice) {
                case 1:
                    listSale.addPromotionsSale();
                    break;
                case 2:
                    listSale.showAllPromotionsSale();
                    listSale.addVoucher();
                    break;
            }

        } while(choice != 3);
    }
}
