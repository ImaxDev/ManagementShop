package src.terminalUI;

import src.dao.Customer;
import src.dao.Product;
import src.service.OrderService;

import java.util.*;

public class OrderUI {
    public static final String PATH = "src/store/order.txt";
    public static final String[] TITLES = {"id","id khach hang","ten khach hang","ten san pham","don gia","so luong","tổng"};
    private OrderService orderService;
    public OrderUI() {
        this.orderService = new OrderService();
        Scanner sc = new Scanner(System.in);
        int n = 0;
        do {

            System.out.println("1 : Hiển thị danh sách hoá đơn");
            System.out.println("2 : Thêm hoá đơn");
            System.out.println("3 : Xoá hoá đơn");
            System.out.println("4 : Tìm kiếm hoá đơn theo tên khách hàng");
            System.out.println("5 : Sắp xếp hoá đơn");
            System.out.println("6 : Thoát");
            System.out.println("Vui lòng chọn chức năng liên quan bằng cách nhập input :");
            n = Integer.parseInt(sc.nextLine());
            switch (n) {
                case 1:
                    getOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
        while (n!=6);
    }
    public void getOrders(){
this.orderService.getOrders();
    }
    public void addOrder(){
        Scanner sc = new Scanner(System.in);
        System.out.println("id hoa don : ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("id khách hàng : ");
        int idCustomer = Integer.parseInt(sc.nextLine());
        System.out.println("So luong san pham");
        int number = Integer.parseInt(sc.nextLine());
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <=number ; i++) {
            System.out.printf("Ma san pham %d  :",i);
            int index = Integer.parseInt(sc.nextLine());
            System.out.printf("So luong %d  :",i);
            int quatity = Integer.parseInt(sc.nextLine());
            map.put(index,quatity);
        }
       this.orderService.addOrder(id,idCustomer,map);
    }
}
