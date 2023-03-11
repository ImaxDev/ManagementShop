package src.service;

import src.dao.Customer;
import src.dao.Product;
import src.utils.FileHandle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ProductService extends CommonServices{
    private FileHandle<Product> fileHandle;
    public ProductService(String fileName) {
        this.fileHandle = new <Customer>FileHandle(fileName);
    }

    public void addProduct(Product product){
        this.fileHandle.writeFile(product.toString());
    }
    public LinkedList<String[]> getProduct(){
        return this.fileHandle.readRowsFile();
    }
    public void deleteProductById(int id){
        LinkedList<String[]> products = this.fileHandle.readRowsFile();
        for (String[] arr:products
        ) {
            if (Integer.parseInt(arr[0])==id){
                products.remove(arr);
                break;
            }

        }
        String str = convertLinkToString(products);
        this.fileHandle.writeFileEmpty(str);
    }
    public void updateProductById(int id,Product product){
        LinkedList<String[]> products = this.fileHandle.readRowsFile();
        for (String[] arr:products
        ) {
            if (Integer.parseInt(arr[0])==id){
                arr[1]=product.getName();
                arr[2]=product.getBrand();
                arr[3]=String.valueOf(product.getPrice());
                arr[4]=product.getCategory();
                arr[5]=String.valueOf(product.getQuantityInStock());
                break;
            }

        }
        String str = convertLinkToString(products);
        this.fileHandle.writeFileEmpty(str);
    }

    public Map<String,LinkedList<String[]>> findProducts(String name, String brand, String category){
        Map<String,LinkedList<String[]>> map = new HashMap<>();
        LinkedList<String[]> customers = this.fileHandle.readRowsFile();
        LinkedList<String[]> resName = new LinkedList<>();
        LinkedList<String[]> resBrand = new LinkedList<>();
        LinkedList<String[]> resCategory = new LinkedList<>();
        customers.forEach(element->{
            if (element[1].equals(name)==true && !name.isEmpty()){
                resName.add(element);
            }
            if (element[2].equals(brand)==true && !brand.isEmpty()){
                resBrand.add(element);
            }
            if (element[4].equals(category)==true && !category.isEmpty()){
                resCategory.add(element);
            }
        });
        map.put("name",resName);
        map.put("brand",resBrand);
        map.put("category",resCategory);
        return  map;
    }
    public LinkedList<String[]> sortCustomer(){
//        Map<String,LinkedList<String[]>> map = new HashMap<>();
//        LinkedList<String[]> customers = this.fileHandle.readRowsFile();
//        LinkedList<String[]> resSTT = new LinkedList<>(customers);
//        LinkedList<String[]> resName = new LinkedList<>(customers);
//        LinkedList<String[]> resPrice = new LinkedList<>(customers);
//        LinkedList<String[]> resQuantityInStock = new LinkedList<>(customers);
//        resSTT.sort((a,b)->a[0].compareTo(b[0]));
//        resName.sort((a,b)->a[1].compareTo(b[1]));
//        resPrice.sort((a,b)->a[3].compareTo(b[3]));
//        resQuantityInStock.sort((a,b)->a[5].compareTo(b[5]));
//        map.put("id",resSTT);
//        map.put("name",resName);
//        map.put("price",resPrice);
//        map.put("quantityInStock",resQuantityInStock);
//        return  map;
        return this.fileHandle.readRowsFile();
    }
}
