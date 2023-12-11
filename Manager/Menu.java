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
            System.out.println("5. Tìm kiếm sản phẩm");
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
            System.out.println("0. Quay lại.");
            System.out.println("1. Tạo chương trình khuyến mãi.");
            System.out.println("2. Hiển thị danh sách chương trình khuyến mãi.");
            System.out.println("3. Xóa chương trình khuyến mãi.");
            System.out.println("4. Thêm voucher.");
            System.out.println("5. Xóa voucher.");
            System.out.println("6. Tìm kiếm chương trình khuyến mãi.");
            System.out.println("7. Tìm kiếm voucher.");
            System.out.println("8. Thay đổi tên chương trình khuyến mãi.");
            choice = new Validate().checkChoiceUser(0,11);
            switch (choice) {
                case 1:
                    listPromotionsSale.addPromotionsSale();
                    break;
                case 2:
                    listPromotionsSale.print();
                    break;
                case 3:
                    listPromotionsSale.deletePromotionsSale();
                    break;
                case 4:
                    listPromotionsSale.addVoucher();
                    break;
                case 5:
                    listPromotionsSale.deleteVoucher();
                    break;
                case 6:
                    listPromotionsSale.findPromotions();
                    break;
                case 7:
                    listPromotionsSale.findVoucher();
                    break;
                case 8:
                    listPromotionsSale.fixNamePromotions();
                    break;
            }

        } while(choice != 0);
    }

    public void billMenu() {
        int choice;
        do {
            System.out.println("=====================HOA DON========================");
            System.out.println("0. Quay lại.");
            System.out.println("1. Tạo hóa đơn mới.");
            System.out.println("2. Lịch sử hóa đơn.");
            System.out.println("3. Tìm kiếm hóa đơn bằng mã.");
            System.out.println("4. Tìm kiếm hóa đơn bằng tên khách hàng.");
            choice = new Validate().checkChoiceUser(0,4);
            switch (choice) {
                case 1:
                    listBill.addBill(listProduct, listStaff, listCustomer, listPromotionsSale);
                    break;
                case 2:
                    listBill.printListOverviewBill();
                    break;
                case 3:
                    listBill.findBillByIdBill();
                    break;
                case 4:
                    listBill.findBillByIdCustomer();
                    break;
            }

        } while(choice != 0);
    }

    public void reportMenu() {
        int choice;
        do {
            System.out.println("=====================THỐNG KÊ========================");
            System.out.println("0. Quay lại.");
            System.out.println("1. Thống kê số lượng sản phẩm đang tồn.");
            System.out.println("2. Thống kê số lượng mã khuyến mãi của từng chương trình.");
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
            System.out.println("0. Quay lại");
            System.out.println("1. Tìm sản phẩm theo mã  phẩm");
            System.out.println("2. Tìm sản phẩm theo tên sản phẩm");
            System.out.println("3. Tìm sản phẩm theo mức giá");
            System.out.println("4. Tìm sản phẩm theo loại và giá");
            choice = new Validate().checkChoiceUser(0, 4);
            switch (choice) {
                case 1:
                    listProduct.findIdProduct();
                    break;
                case 2:
                    listProduct.findNameProduct();
                    break;
                case 3:
                    listProduct.findProductByPrice();
                    break;
                case 4:
                    listProduct.findProductByTypeProductNPrice();
                    break;
            }
        }while(choice != 0);
    }

    public void showSubMenu() {
        int choice;
        do {
            System.out.println("=====================HIỂN THỊ DANH SÁCH========================");
            System.out.println("0. Quay lại.");
            System.out.println("1. Hiển thị danh sách sản phẩm.");
            System.out.println("2. Hiển thị danh sách phiếu nhập.");
            choice = new Validate().checkChoiceUser(0, 2);
            switch (choice) {
                case 1:
                    listProduct.showProduct(false);
                    break;
                case 2:
                    listBillImport.show();
                    break;
            }

        } while(choice != 0);
    }
}