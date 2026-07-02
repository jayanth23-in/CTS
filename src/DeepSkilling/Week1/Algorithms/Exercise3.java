package DeepSkilling.Week1.Algorithms;

class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int od, String cN, double tP) {
        this.orderId = od;
        this.customerName = cN;
        this.totalPrice = tP;
    }

    int getOrderId() {
        return orderId;
    }

    String getCustomerName() {
        return customerName;
    }

    double getTotalPrice() {
        return totalPrice;
    }

    public String toString() {
        return "OrderId: " + orderId + ", Customer: " + customerName + ", Total: " + totalPrice;
    }
}

class OrderSorter {

    void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}

public class Exercise3 {
    public static void main(String[] args) {
        Order[] bubbleOrders = {
                new Order(101, "Alice", 250.75),
                new Order(102, "Bob", 89.99),
                new Order(103, "Charlie", 430.00),
                new Order(104, "Diana", 12.50),
                new Order(105, "Ethan", 199.99)
        };

        Order[] quickOrders = {
                new Order(101, "Alice", 250.75),
                new Order(102, "Bob", 89.99),
                new Order(103, "Charlie", 430.00),
                new Order(104, "Diana", 12.50),
                new Order(105, "Ethan", 199.99)
        };

        OrderSorter sorter = new OrderSorter();

        sorter.bubbleSort(bubbleOrders);
        System.out.println("Bubble Sort Result:");
        for (Order o : bubbleOrders) {
            System.out.println(o);
        }

        sorter.quickSort(quickOrders, 0, quickOrders.length - 1);
        System.out.println("Quick Sort Result:");
        for (Order o : quickOrders) {
            System.out.println(o);
        }
    }
}