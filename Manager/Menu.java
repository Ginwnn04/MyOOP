package DoAnOOP.Manager;

public class Menu {
    private ListProduct listProduct = new ListProduct();
    private ListPromotionsSale listPromotionsSale = new ListPromotionsSale();
    private ListBillImport listBillImport = new ListBillImport();
    private ListBill listBill = new ListBill();
    private ListCustomer listCustomer = new ListCustomer();
    private ListStaff listStaff = new ListStaff();
    private ListSupplier listSupplier = new ListSupplier();
    public Menu() {
        listProduct.readData();
        listPromotionsSale.readData();
        listBill.readData();
        listBillImport.readData();
        listCustomer.readData();
        listStaff.readData();
        listSupplier.readData();
    }
    public void printMenuEmployee() {
        int choice;
        do {
            System.out.println("==================QUẢN LÝ CỬA HÀNG=====================");
            System.out.println("0. Dừng chương trình.");
            System.out.println("1. Thêm sản phẩm.");
            System.out.println("2. Thêm số lượng sản phẩm.");
            System.out.println("3. Hiển thị danh sách.");
            System.out.println("4. Sửa thông tin sản phẩm");
            System.out.println("5. Tìm kiếm tin sản phẩm");
            System.out.println("6. Xoá sản phẩm");
            System.out.println("7. Thống kê.");
            System.out.println("8. Chương trình khuyến mãi");
            System.out.println("9. Tạo hoá đơn.");
            choice = new Validate().checkChoiceUser(0, 9);
            switch (choice) {
                case 1:
                    // Them san pham
                    listBillImport.addBillImport(listProduct, listStaff, listSupplier, true);
                    break;
                case 2:
                    // Them so luong san pham
                    listBillImport.addBillImport(listProduct, listStaff, listSupplier, false);

                    break;
                case 3:
                    showSubMenu();
                    break;
                case 4:
                    listProduct.updateProduct();
                    break;
                case 5:
                    SubMenufind();
                    break;
                case 6:
                    listProduct.deleteProduct();
                    break;
                case 7:
                    reportMenu();
                    break;
                case 8:
                    // Chuong trinh khuyen mai
                    promotionsSaleMenu();
                    break;
                case 9:
                    billMenu();
                    break;
            }
        } while(choice != 0);
        listProduct.writeData(false);
        listPromotionsSale.writeData(false);
        listBill.writeData(false);
        listBillImport.writeData(false);
        listCustomer.writeData(false);
        listStaff.writeData(false);
        listSupplier.writeData(false);
    }


    public void promotionsSaleMenu() {
        int choice;
        do {
            System.out.println("=====================TẠO CHƯƠNG TRÌNH KHUYẾN MÃI========================");
            System.out.println("1. Tạo chương trình khuyến mãi.");
            System.out.println("2. Hiển thị danh sách chương trình khuyến mãi.");
            System.out.println("3. Thêm chương trình khuyến mãi.");
            System.out.println("4. Xóa chương trình khuyến mãi.");
            System.out.println("5. Thêm voucher.");
            System.out.println("6. Xóa voucher.");
            System.out.println("7. Tìm kiếm chương trình khuyến mãi.");
            System.out.println("8. Tìm kiếm voucher.");
            System.out.println("9. Thay đổi tên chương trình khuyến mãi.");

            System.out.println("0. Quay lại.");
            choice = new Validate().checkChoiceUser(0,11);
            switch (choice) {
                case 1:
                    listPromotionsSale.input();
                    break;
                case 2:
                    listPromotionsSale.print();
                    break;
                case 3:
                    listPromotionsSale.addPromotionsSale();
                    break;
                case 4:
                    listPromotionsSale.deletePromotionsSale();
                    break;
                case 5:
                    listPromotionsSale.addVoucher();
                    break;
                case 6:
                    listPromotionsSale.deleteVoucher();
                    break;
                case 7:
                    listPromotionsSale.findPromotions();
                    break;
                case 8:
                    listPromotionsSale.findVoucher();
                    break;
                case 9:
                    listPromotionsSale.fixNamePromotions();
                    break;
            }

        } while(choice != 0);
    }

    public void billMenu() {
        int choice;
        do {
            System.out.println("=====================HOA DON========================");
            System.out.println("1. Tạo hóa đơn mới.");
            System.out.println("2. Lịch sử hóa đơn.");
            System.out.println("3. Thây đổi số lượng sản phẩm mua.");
            System.out.println("4. Mua thêm sản phẩm.");
            System.out.println("5. Xóa bớt sản phẩm.");
            System.out.println("6. Tìm kiếm hóa đơn bằng mã.");
            System.out.println("7. Tìm kiếm hóa đơn bằng ngày xuất.");
            System.out.println("8. Tìm kiếm hóa đơn bằng mã nhân viên.");
            System.out.println("9. Tìm kiếm hóa đơn bằng tên khách hàng.");
            System.out.println("0. Quay lại.");
            choice = new Validate().checkChoiceUser(0,11);
            switch (choice) {
                case 1:
                    listBill.addBill(listProduct, listStaff, listCustomer, listPromotionsSale);
                    break;
                case 2:
                    listBill.printListBill();
                    break;
                case 3:
                    listBill.fixQuantityProduct();
                    break;
                case 4:
//                    listBill.addProduct();
                    break;
                case 5:
                    listBill.deleteProduct();
                    break;
                case 6:
                    listBill.findBillByIdBill();
                    break;
                case 7:
//                    listBill.findBillByPrintDate();
                    break;
                case 8:
                    listBill.findBillByIdEmployee();
                    break;
                case 9:
                    listBill.findBillByIdCustomer();
                    break;
            }

        } while(choice != 0);
    }

    public void reportMenu() {
        int choice;
        do {
            System.out.println("=====================THỐNG KÊ========================");
            System.out.println("1. Thống kê số lượng sản phẩm đang tồn.");
            System.out.println("2. Thống kê số lượng mã khuyến mãi của từng chương trình.");
            System.out.println("0. Quay lại.");
            choice = new Validate().checkChoiceUser(0,2);
            switch (choice) {
                case 1:
                    listProduct.reportProductCurrent();
                    break;
                case 2:
                    listPromotionsSale.reportPromotion();
                    break;
            }

        } while(choice != 0);
    }

    public void SubMenufind(){
        int choice;
        do{
            System.out.println("=====================TÌM SẢN PHẨM========================");
            System.out.println("1.Tìm sản phẩm theo mã sản phẩm");
            System.out.println("2.Tìm sản phẩm theo tên sản phẩm");
            System.out.println("3.Quay lại");
            choice = new Validate().checkChoiceUser(1, 3);
            switch (choice) {
                case 1:
                    listProduct.findIdProduct();
                    break;
                case 2:
                    listProduct.findNameProduct();
                    break;
            }
        }while(choice != 3);
    }

    public void showSubMenu() {
        int choice;
        do {
            System.out.println("=====================HIỂN THỊ DANH SÁCH========================");
            System.out.println("1. Hiển thị danh sách sản phẩm.");
            System.out.println("2. Hiển thị danh sách phiếu nhập.");
            System.out.println("3. Quay lại.");
            choice = new Validate().checkChoiceUser(1, 3);
            switch (choice) {
                case 1:
                    listProduct.showProduct(false);
                    break;
                case 2:
                    listBillImport.show();
                    break;
            }

        } while(choice != 3);
    }
}