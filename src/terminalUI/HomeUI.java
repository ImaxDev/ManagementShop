package src.terminalUI;

import java.util.Scanner;

public class HomeUI {
    public HomeUI() {
        Scanner sc = new Scanner(System.in);
        int n=0;
        do{
            System.out.println("1 : Khách hàng");
            System.out.println("2 : Sản Phẩm");
            System.out.println("3 : Đặt Hàng");
            System.out.println("4 : Kết thúc");
            System.out.println("Vui lòng chọn chức năng liên quan bằng cách nhập input :");
            n=Integer.parseInt(sc.nextLine());
            switch (n){
                case 1:
                    System.out.println("KHÁCH HÀNG :");
                    CustomerUI customerUI = new CustomerUI();
                    break;
                case 2:
                    System.out.println("SẢN PHẨM :");
                    ProductUI productUI = new ProductUI();
                    break;
                case 3:
                    System.out.println("ĐẶT HÀNG :");
                    OrderUI orderUI = new OrderUI();
                    break;
                default:
                    System.out.println("END !!!");
                    break;
            }
        }
        while (n!=4);

    }

    public static void main(String[] args) {
        HomeUI homeUI = new HomeUI();
    }
}
