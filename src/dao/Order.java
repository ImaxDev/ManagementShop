package src.dao;

import java.util.List;
import java.util.Map;

public class Order {
    private int id;
    private int customerId;
    private Map<Integer,Integer> orders;


    public Order(int id, int customerId, Map<Integer, Integer> orders) {
        this.id = id;
        this.customerId = customerId;
        this.orders = orders;
    }

    public Order() {
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Map<Integer, Integer> getOrders() {
        return orders;
    }

    public void setOrders(Map<Integer, Integer> orders) {
        this.orders = orders;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : orders.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("-");
        }
        sb.setLength(sb.length() - 1); // Remove the last "-"
        String mapAsString = sb.toString();
        return id + ","+ customerId+ ","+mapAsString ;
    }
}
