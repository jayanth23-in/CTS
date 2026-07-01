
package Algorithms;
import java.util.*;
//1.The essentiality of handling large inventories using DSA comes down to efficiency
//  A few types of structures that can be used are :
    // HashMap , ArrayList
    //For this problem will use hashmap as we can have more control over different products with just their IDs


import java.util.Map;

class Product1{
    String productID;
    String productName ;
    int quantity;
    double price;

    //Apply SOLID principles
    public Product1(String pid , String pn , int q , double p){
        this.productID = pid;
        this.productName = pn;
        this.quantity = q;
        this.price = p;
    }


    public String getProductId() { return productID; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setProductName(String productName) { this.productName = productName; }
    //public void setQuantity(int quantity) { this.quantity = quantity; }
    //public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "ID: " + productID + " | Name: " + productName + " | Qty: " + quantity + " | Price: $" + price;
    }

}

class InventoryM{
    //Class to manage the inventory of products
    Map<String , Product1> pMap = new HashMap<>();

    //add the product
    //decoupling methods


    public void addP(Product1 product){
        if(pMap.containsKey(product.getProductId())){
            System.out.println(product.getProductId() + " already exists.");
            return;
        }
        pMap.put(product.getProductId() , product);
        System.out.println("Added: " + product.getProductName());

    }


    public void updateP(String productId, String newName, Integer newQty, Double newPrice){
        if(!pMap.containsKey(productId)){
            System.out.println("Product not found");
            return;
        }

        Product1 pro = pMap.get(productId);

        if(newName != null){
            pro.setProductName(newName);
        }
        if(newQty != null){
            pro.setQuantity(newQty);
        }
        if(newPrice != null) {
            pro.setPrice(newPrice);
        }
    }

    public void deleteP(String productID){
        if(pMap.containsKey(productID)){
            Product1 remove = pMap.remove(productID);
            System.out.println("Deleted: " + remove.getProductName());
            //Remove the specific product
        }else{
            System.out.println("Error: Product ID not found.");
        }
    }


    public void displayInventory() {
        if (pMap.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        for (Product1 p : pMap.values()) {
            System.out.println(p);
        }
    }
}
public class Exercise1 {
    public static void main(String[] args) {
        //Only the HashMap has to be intialized.
        InventoryM warehouse = new InventoryM();

        // 1. Add some test products
        System.out.println("--- Testing Add ---");
        warehouse.addP(new Product1("P101", "Laptop", 15, 999.99));
        warehouse.addP(new Product1("P102", "Wireless Mouse", 50, 24.99));
        warehouse.addP(new Product1("P103", "Mechanical Keyboard", 25, 89.99));

        warehouse.displayInventory();

        // 2. Update a product's quantity and price
        System.out.println("\n--- Testing Update ---");
        warehouse.updateP("P102", null, 45, 19.99); // Price dropped, 5 sold

        // 3. Delete a product
        System.out.println("\n--- Testing Delete ---");
        warehouse.deleteP("P103");

        // 4. View final inventory
        warehouse.displayInventory();
    }
}


//Each and every operation's TC is O(1)
