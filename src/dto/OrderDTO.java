package src.dto;

import src.dao.Customer;
import src.dao.Product;

import java.util.LinkedList;

public class OrderDTO {
    private int id;
    private Customer customer;
    private LinkedList< ItemProductOrderDTO> products;
    private double totalPrice;

    public OrderDTO(int id, Customer customer, LinkedList<ItemProductOrderDTO> products, double totalPrice) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LinkedList<ItemProductOrderDTO> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<ItemProductOrderDTO> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
