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
    private double TotalSum = 0;
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
    public LinkedList<String[]> getOrders(){
        return readFileResponseDTO(this.fileHandleOrder.readRowsFile());
    }
    public LinkedList<String[]> findOrdersByNameCustomer(String name){
        LinkedList<String[]> res = new LinkedList<>();
        for (String[] s:readFileResponseDTO(this.fileHandleOrder.readRowsFile())
             ) {
            if (s[2].equals(name)) res.add(s);
        }
        return  res;
    }
    public LinkedList<String[]> sortOrderByTotal (){
        LinkedList<String[]> res= readFileResponseDTO(this.fileHandleOrder.readRowsFile());
     res.sort((x,y)->Double.compare(Double.parseDouble(x[4]),Double.parseDouble(y[4])));
     return res;

    }
    public void statisticalOrder(double filNumber){
        LinkedList<String[]> input=this.fileHandleOrder.readRowsFile();
        double minOrder=converDaoToDto(convertArrTpDao(input.get(0))).getTotalPrice(),maxOder=0,countsB=0,countsS=0;
        for (String[] e:input
        ) {
            OrderDTO orderDTO = converDaoToDto(convertArrTpDao(e));
            minOrder =Math.min(minOrder,orderDTO.getTotalPrice());
            maxOder = Math.max(maxOder,orderDTO.getId());
            if (orderDTO.getTotalPrice()>= filNumber){
                countsB++;
            }
            else{
                countsS++;
            }
        };
        System.out.printf("DOANH THU HOÁ ĐƠN LỚN NHẤT : %.1f \n",maxOder);
        System.out.printf("DOANH THU HOÁ ĐƠN NHỎ NHẤT : %.1f \n",minOrder);
        System.out.printf("SỐ HOÁ ĐƠN CÓ DOANH THU LỚN HƠN HOẶC BẰNG %.1f là : %.1f \n",filNumber,countsB);
        System.out.printf("SỐ HOÁ ĐƠN CÓ DOANH THU NHỎ HƠN HOẶC BẰNG %.1f là : %.1f \n",filNumber,countsS);
    }
    private  LinkedList<String[]> readFileResponseDTO(LinkedList<String[]> input){
        LinkedList<String[]> orderDTOS = new LinkedList<>();
        double sum =0;

            for (String[] e:input
                 ) {
            OrderDTO orderDTO = converDaoToDto(convertArrTpDao(e));
            String newStr="";
            for (ItemProductOrderDTO product: orderDTO.getProducts()
                 ) {
                sum+=product.getTotal();
                newStr+=  "("+product.getName()+", "+String.valueOf(product.getQuantity()+", "+String.valueOf(product.getTotal())+"); ");
            }
            String[] ele = {String.valueOf(orderDTO.getId()), orderDTO.getCustomer().getLastName(),orderDTO.getCustomer().getEmail(),newStr, String.valueOf(orderDTO.getTotalPrice())};
            orderDTOS.add(ele);

        };
            this.TotalSum=sum;
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
       Customer customer=this.customers.stream().filter(e->e.getId()==order.getId()).findAny().get();
        LinkedList<ItemProductOrderDTO> items = new LinkedList<>();
        double sum =0;
        for (Map.Entry<Integer, Integer> entry : order.getOrders().entrySet()) {
            Product product = (Product) this.products.stream().filter(e->e.getId()==entry.getKey()).findAny().get();
            ItemProductOrderDTO item = new ItemProductOrderDTO(product.getName(),product.getPrice(),entry.getValue());
            items.add(item);
            sum+=item.getTotal();
        }
        return  new OrderDTO(order.getId(),customer,items,sum);
    }

    public double getTotalSum() {
        return TotalSum;
    }

    public void setTotalSum(double totalSum) {
        TotalSum = totalSum;
    }
}
