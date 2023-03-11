package src.terminalUI;

import src.dao.Customer;
import src.dao.Product;
import src.service.CustomerService;
import src.service.ProductService;
import src.utils.ObjectFormatTable;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class ProductUI {
    public static final String PATH = "src/store/product.txt";
    public static final String[] TITLES = {"id","name","brand","price","category","quantityInStock"};
    private ProductService productService;
    public ProductUI(){
        this.productService = new ProductService(PATH);
        Scanner sc = new Scanner(System.in);
        int n=0;
        do{
            System.out.println("1 : Hiển thị danh sách sản phẩm");
            System.out.println("2 : Thêm sản phẩm");
            System.out.println("3 : Xoá sản phẩm");
            System.out.println("4 : Tìm kiếm sản phẩm");
            System.out.println("5 : Sắp xếp sản phẩm");
            System.out.println("6 : Thoát");
            System.out.println("Vui lòng chọn chức năng liên quan bằng cách nhập input :");
            n=Integer.parseInt(sc.nextLine());
            switch (n){
                case 1:
                    System.out.println("HIỂN THỊ DANH SÁCH SẢN PHẨM:");
                    getProducts();
                    break;
                case 2:
                    System.out.println("THÊM SẢN PHẨM:");
                    addProduct();
                    break;
                case 3:
                    System.out.println("XOÁ SẢN PHẨM:");
                    deleteProductById();
                    break;
                case 4:
                    System.out.println("TÌM KIẾM SẢN PHẨM:");
                    searchProduct();
                    break;
                case 5:
                    System.out.println("SẮP SẾP DANH SÁCH SẢN PHẨM:");
                    sortProduct();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("VUI LÒNG NHẬP ĐÚNG THÔNG TIN");
                    break;
            }
        }
        while (n!=6);
    }
    public void getProducts(){
        LinkedList<String[]> customers = this.productService.getProduct();

        new ObjectFormatTable(TITLES,customers).printTable();

    }
    public void addProduct(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("STT : ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("Tên : ");
            String name = sc.nextLine();
            System.out.println("Brand : ");
            String brand = sc.nextLine();
            System.out.println("Price : ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.println("Category : ");
            String catefory = sc.nextLine();
            System.out.println("QuantityInStock :");
            int quantityInStock = Integer.parseInt(sc.nextLine());
            this.productService.addProduct(new Product(id, name, brand,price,catefory,quantityInStock));
            System.out.println("ADD SUCCESS !!!");
        }
        catch (Exception ex){
            System.out.println("ADD FAIL !!!");
        }
    }
    public void deleteProductById(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("STT : ");
            int id = Integer.parseInt(sc.nextLine());
            this.productService.deleteProductById(id);
            System.out.println("DELETE SUCCESS !!!");
        }
        catch (Exception ex){
            System.out.println("DELETE FAIL !!!");
        }
    }
    public void searchProduct(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Tìm kiếm theo tên : ");
            String name = sc.nextLine();
            System.out.println("Tìm kiếm theo brand : ");
            String brand = sc.nextLine();
            System.out.println("Tìm kiếm theo category : ");
            String category = sc.nextLine();
            Map<String,LinkedList<String[]>> map= this.productService.findProducts(name,brand,category);
            System.out.println("Kết quả tìm kiếm theo tên :");
            new ObjectFormatTable(TITLES,map.get("name")).printTable();
            System.out.println("Kết quả tìm kiếm theo brand :");
            new ObjectFormatTable(TITLES,map.get("brand")).printTable();
            System.out.println("Kết quả tìm kiếm theo số category :");
            new ObjectFormatTable(TITLES,map.get("category")).printTable();
        }
        catch (Exception ex){
            System.out.println("SEARCH FAIL !!!");
        }
    }
    public void sortProduct(){
        try{
           LinkedList<String[]> linked= this.productService.sortCustomer();
            System.out.println("Kết quả sắp xếp theo stt:");
            linked.sort((a,b)->Integer.compare(Integer.parseInt(a[0]),Integer.parseInt(b[0])));
            new ObjectFormatTable(TITLES,linked).printTable();
            System.out.println("Kết quả sắp xếp theo tên :");
            linked.sort((a,b)->a[1].compareTo(b[1]));
            new ObjectFormatTable(TITLES,linked).printTable();
            new ObjectFormatTable(TITLES,linked).printTable();
            System.out.println("Kết quả sắp xếp theo số lượng tồn kho :");
            linked.sort((a,b)->Integer.compare(Integer.parseInt(a[5]),Integer.parseInt(b[5])));
            new ObjectFormatTable(TITLES,linked).printTable();
        }
        catch (Exception ex){
            System.out.println("SORT FAIL !!!");
        }
    }
}
