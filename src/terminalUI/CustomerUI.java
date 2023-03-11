package src.terminalUI;

import src.dao.Customer;
import src.service.CustomerService;
import src.utils.ObjectFormatTable;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CustomerUI {
    public static final String PATH = "src/store/customer.txt";
    public static final String[] TITLES = {"id","first name","last name","email","phone number"};
    private CustomerService customerService;
    public CustomerUI(){
        this.customerService = new CustomerService(PATH);
        Scanner sc = new Scanner(System.in);
        int n=0;
        do{
            System.out.println("1 : Hiển thị danh sách khách hàng");
            System.out.println("2 : Thêm khách hàng");
            System.out.println("3 : Xoá khách hàng");
            System.out.println("4 : Sửa khách hàng");
            System.out.println("5 : Tìm kiếm khách hàng");
            System.out.println("6 : Sắp xếp khách hàng");
            System.out.println("7 : Thoát");
            System.out.println("Vui lòng chọn chức năng liên quan bằng cách nhập input :");
            n=Integer.parseInt(sc.nextLine());
            switch (n){
                case 1:
                    System.out.println("HIỂN THỊ DANH SÁCH KHÁCH HÀNG:");
                    getCustomers();
                    break;
                case 2:
                    System.out.println("THÊM KHÁCH HÀNG:");
                    addCustomer();
                    break;
                case 3:
                    System.out.println("XOÁ KHÁCH HÀNG:");
                    deleteCustomerById();
                    break;
                case 4:
                    System.out.println("SỬA KHÁCH HÀNG:");
                    updateCustomer();
                    break;
                case 5:
                    System.out.println("TÌM KIẾM KHÁCH HÀNG:");
                    searchCustomer();
                    break;
                case 6:
                    System.out.println("SẮP SẾP DANH SÁCH KHÁCH HÀNG:");
                    sortCustomer();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("VUI LÒNG NHẬP ĐÚNG THÔNG TIN");
                    break;
            }
        }
        while (n!=7);
    }
    public void getCustomers(){
       LinkedList<String[]> customers = this.customerService.getCustomers();

        new ObjectFormatTable(TITLES,customers).printTable();

    }
    public void addCustomer(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("STT : ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("Tên : ");
            String firstName = sc.nextLine();
            System.out.println("Họ : ");
            String lastName = sc.nextLine();
            System.out.println("Email : ");
            String email = sc.nextLine();
            System.out.println("SDT : ");
            String phoneNumber = sc.nextLine();
            this.customerService.addCustomer(new Customer(id, firstName, lastName, email, phoneNumber));
            System.out.println("ADD SUCCESS !!!");
        }
        catch (Exception ex){
            System.out.println("ADD FAIL !!!");
        }
    }
    public void deleteCustomerById(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("STT : ");
            int id = Integer.parseInt(sc.nextLine());
            this.customerService.deleteCustomerById(id);
            System.out.println("DELETE SUCCESS !!!");
        }
        catch (Exception ex){
            System.out.println("DELETE FAIL !!!");
        }
    }
    public void searchCustomer(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Tìm kiếm theo tên : ");
            String lastName = sc.nextLine();
            System.out.println("Tìm kiếm theo tên : ");
            String email = sc.nextLine();
            System.out.println("Tìm kiếm theo tên : ");
            String phoneNumber = sc.nextLine();
            Map<String,LinkedList<String[]>> map= this.customerService.findCustomers(lastName,email,phoneNumber);
            System.out.println("Kết quả tìm kiếm theo tên :");
            new ObjectFormatTable(TITLES,map.get("lastName")).printTable();
            System.out.println("Kết quả tìm kiếm theo email :");
            new ObjectFormatTable(TITLES,map.get("email")).printTable();
            System.out.println("Kết quả tìm kiếm theo số điện thoại :");
            new ObjectFormatTable(TITLES,map.get("phoneNumber")).printTable();
        }
        catch (Exception ex){
            System.out.println("SEARCH FAIL !!!");
        }
    }
    public void sortCustomer(){
        try{
            Map<String,LinkedList<String[]>> map= this.customerService.sortCustomer();
            System.out.println("Kết quả sắp xếp theo stt:");
            new ObjectFormatTable(TITLES,map.get("id")).printTable();
            System.out.println("Kết quả sắp xếp theo tên :");
            new ObjectFormatTable(TITLES,map.get("lastName")).printTable();
        }
        catch (Exception ex){
            System.out.println("SORT FAIL !!!");
        }
    }
    public void updateCustomer(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("STT : ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("Tên : ");
            String firstName = sc.nextLine();
            System.out.println("Họ : ");
            String lastName = sc.nextLine();
            System.out.println("Email : ");
            String email = sc.nextLine();
            System.out.println("SDT : ");
            String phoneNumber = sc.nextLine();
            this.customerService.updateCustomerById(id, new Customer(id, firstName, lastName, email, phoneNumber));
            System.out.println("UPDATE THÀNH CÔNG ");
        }catch(Exception ex){
            System.out.println("UPDATE THAT BAI");
        }
    }
}
