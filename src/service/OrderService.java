package src.service;

import src.dao.Customer;
import src.dao.Order;
import src.dao.Product;
import src.dto.ItemProductOrderDTO;
import src.dto.OrderDTO;
import src.utils.FileHandle;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

public class OrderService {
    private FileHandle<Customer> fileHandleCustomer;
    private FileHandle<Product> fileHandleProduct;
    private FileHandle<Order> fileHandleOrder;
    private LinkedList<Customer> customers;
    private LinkedList<Product> products = new LinkedList<>();
    private LinkedList<Order> orders= new LinkedList<>();
    private  void initData(){
        this.fileHandleProduct = new  <Product>FileHandle("src/store/product.txt");
        this.fileHandleCustomer = new  <Product>FileHandle("src/store/customer.txt");
        this.fileHandleOrder = new  <Product>FileHandle("src/store/order.txt");
        LinkedList<String[]> customerStringArr=this.fileHandleCustomer.readRowsFile();
        LinkedList<String[]> productStringArr=this.fileHandleProduct.readRowsFile();

        this.customers = new LinkedList<>();
        for (String[] e:customerStringArr
             ) {
            this.customers.add(new Customer(Integer.parseInt(e[0]),e[1],e[2],e[3],e[4]));
        }
        for (String[] e:productStringArr
             ) {
            this.products.add(new Product(Integer.parseInt(e[0]),e[1],e[2],Double.parseDouble(e[3]),e[4],Integer.parseInt(e[5])));
        }
        this.orders = new LinkedList<>();
    }
    public OrderService() {
        initData();
    }
    public void addOrder (int id,int customerId, Map<Integer, Integer> order){
       this.fileHandleOrder.writeFile( new Order(id,customerId,order).toString());
    }
    public LinkedList<OrderDTO> getOrders(){
        return readFileResponseDTO(this.fileHandleOrder.readRowsFile());
    }

    private  LinkedList<OrderDTO> readFileResponseDTO(LinkedList<String[]> input){
        LinkedList<OrderDTO> orderDTOS = new LinkedList<>();
        input.forEach(e->{
            orderDTOS.add(converDaoToDto(convertArrTpDao(e)));
        });
        return orderDTOS;
    }
    private Order convertArrTpDao (String[] str){
        String[] orderMap = str[2].split("-");
        Map<Integer,Integer> map = new HashMap<>();
        for (String ele: orderMap
             ) {
            String[] st = ele.split(":");
            map.put(Integer.parseInt(st[0]),Integer.parseInt(st[0]));
        }

        return  new Order(Integer.parseInt(str[0]),Integer.parseInt(str[1]),map);
    }
    private OrderDTO converDaoToDto (Order order){
        Stream<Customer> filteredStream=this.customers.stream().filter(e->e.getId()==order.getId());
        Customer cus=filteredStream.findFirst().get();
        Customer customer = (Customer) this.customers.stream().filter(e->e.getId()==order.getId());
        LinkedList<ItemProductOrderDTO> items = new LinkedList<>();
        double sum =0;
        for (Map.Entry<Integer, Integer> entry : order.getOrders().entrySet()) {
            Product product = (Product) this.products.stream().filter(e->e.getId()==entry.getKey());
            ItemProductOrderDTO item = new ItemProductOrderDTO(product.getName(),product.getPrice(),entry.getValue());
            items.add(item);
            sum+=item.getTotal();
        }
        return  new OrderDTO(order.getId(),customer,items,sum);
    }

}
