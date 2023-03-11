package src.service;

import src.dao.Customer;
import src.terminalUI.CustomerUI;
import src.utils.FileHandle;

import java.util.*;

public class CustomerService extends CommonServices{
    private FileHandle<Customer> fileHandle;
    public CustomerService(String fileName) {
        this.fileHandle = new <Customer>FileHandle(fileName);
    }

    public void addCustomer(Customer customer){
       this.fileHandle.writeFile(customer.toString());
    }
    public LinkedList<String[]> getCustomers(){
        return this.fileHandle.readRowsFile();
    }
    public void deleteCustomerById(int id){
        LinkedList<String[]> customers = this.fileHandle.readRowsFile();
        for (String[] arr:customers
             ) {
            if (Integer.parseInt(arr[0])==id){
                customers.remove(arr);
                break;
            }

        }
        String str = convertLinkToString(customers);
      this.fileHandle.writeFileEmpty(str);
    }
    public void updateCustomerById(int id, Customer customer){
        LinkedList<String[]> customers = this.fileHandle.readRowsFile();
        for (String[] arr:customers
        ) {
            if (Integer.parseInt(arr[0])==id){
                arr[1]=customer.getFirstName();
                arr[2]=customer.getLastName();
                arr[3]=customer.getEmail();
                arr[4]=customer.getPhone();
                break;
            }

        }
        String str = convertLinkToString(customers);
        this.fileHandle.writeFileEmpty(str);
    }

    public Map<String,LinkedList<String[]>> findCustomers(String lastName, String email, String phoneNumber){
        Map<String,LinkedList<String[]>> map = new HashMap<>();
        LinkedList<String[]> customers = this.fileHandle.readRowsFile();
        LinkedList<String[]> resName = new LinkedList<>();
        LinkedList<String[]> resEmail = new LinkedList<>();
        LinkedList<String[]> resPhoneNumber = new LinkedList<>();
        customers.forEach(element->{
            if (element[2].equals(lastName)==true && !lastName.isEmpty()){
                resName.add(element);
            }
            if (element[3].equals(email)==true && !email.isEmpty()){
                resEmail.add(element);
            }
            if (element[4].equals(phoneNumber)==true && !phoneNumber.isEmpty()){
                resPhoneNumber.add(element);
            }
        });
        map.put("lastName",resName);
        map.put("email",resEmail);
        map.put("phoneNumber",resPhoneNumber);
        return  map;
    }
    public Map<String,LinkedList<String[]>> sortCustomer(){
        Map<String,LinkedList<String[]>> map = new HashMap<>();
        LinkedList<String[]> customers = this.fileHandle.readRowsFile();
        LinkedList<String[]> tmp = new LinkedList<>(customers);
        LinkedList<String[]> resSTT = new LinkedList<>();
        LinkedList<String[]> resEmail = new LinkedList<>();
        LinkedList<String[]> resLastName = new LinkedList<>();
        tmp.sort((a,b)->a[0].compareTo(b[0]));
        resSTT = tmp;
        customers.sort((a,b)->a[2].compareTo(b[2]));
        resLastName = customers;
        map.put("id",resSTT);
        map.put("lastName",resLastName);
        return  map;
    }

}

