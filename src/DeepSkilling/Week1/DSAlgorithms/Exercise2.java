
package DeepSkilling.Week1.DSAlgorithms;

//O notation -
//Best , AVG , WORST case

import java.util.Arrays;
import java.util.Comparator;

class Product2 {
    String productID;
    String productName;
    String category;

    public Product2(String pid , String pn , String cat){
        this.productID = pid;
        this.productName = pn;
        this.category = cat;
    }

    String getProductID(){
        return productID;
    }
    String getProductName(){
        return productName;
    }
    String getProductCat(){
        return category;
    }
}
//We can a seperate function called ECom . There would be methods such search(BinarySearch and Linear search) and add

class ECom{
    Product2[] arr;
    int size;
    int capacity;

    public ECom(int capacity) {
        this.capacity = capacity;
        this.arr = new Product2[capacity];
        this.size = 0;
    }

    void add(Product2 p) {
        if (size < capacity) {
            arr[size] = p;
            size++;
        }
    }

    Product2 linearSearch(String productID) {
        for (int i = 0; i < size; i++) {
            if (arr[i].getProductID().equals(productID)) {
                return arr[i];
            }
        }
        return null;
    }

    Product2 binarySearch(String productID) {
        Product2[] sortedArr = Arrays.copyOf(arr, size);
        Arrays.sort(sortedArr, Comparator.comparing(Product2::getProductID));

        int low = 0;
        int high = sortedArr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = sortedArr[mid].getProductID().compareTo(productID);

            if (cmp == 0) {
                return sortedArr[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
public class Exercise2 {
    public static void main(String[] args) {
        ECom store = new ECom(10);

        store.add(new Product2("P103", "Wireless Mouse", "Electronics"));
        store.add(new Product2("P101", "Bluetooth Speaker", "Electronics"));
        store.add(new Product2("P105", "Running Shoes", "Footwear"));
        store.add(new Product2("P102", "Yoga Mat", "Fitness"));
        store.add(new Product2("P104", "Desk Lamp", "Home"));

        Product2 linearResult = store.linearSearch("P105");
        if (linearResult != null) {
            System.out.println("Linear Search Found: " + linearResult.getProductName());
        } else {
            System.out.println("Linear Search: Product not found");
        }

        Product2 binaryResult = store.binarySearch("P102");
        if (binaryResult != null) {
            System.out.println("Binary Search Found: " + binaryResult.getProductName());
        } else {
            System.out.println("Binary Search: Product not found");
        }
    }
}
