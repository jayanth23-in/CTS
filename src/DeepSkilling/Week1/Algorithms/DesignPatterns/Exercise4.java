package DeepSkilling.Week1.Algorithms.DesignPatterns;

interface PaymentProcessor {
    void processPayment(double amount);
}

class StripeGateway {
    void makeTransaction(double amountInDollars) {
        System.out.println("Stripe: Processing transaction of $" + amountInDollars);
    }
}

class PayPalGateway {
    void sendPayment(double amountInDollars) {
        System.out.println("PayPal: Sending payment of $" + amountInDollars);
    }
}

class RazorpayGateway {
    void pay(double amountInRupees) {
        System.out.println("Razorpay: Paying INR " + amountInRupees);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    public void processPayment(double amount) {
        stripeGateway.makeTransaction(amount);
    }
}

class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    public void processPayment(double amount) {
        payPalGateway.sendPayment(amount);
    }
}

class RazorpayAdapter implements PaymentProcessor {
    private RazorpayGateway razorpayGateway;

    public RazorpayAdapter(RazorpayGateway razorpayGateway) {
        this.razorpayGateway = razorpayGateway;
    }

    public void processPayment(double amount) {
        razorpayGateway.pay(amount * 83);
    }
}

public class Exercise4 {
    public static void main(String[] args) {
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        stripeProcessor.processPayment(100.00);

        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPalGateway());
        payPalProcessor.processPayment(75.50);

        PaymentProcessor razorpayProcessor = new RazorpayAdapter(new RazorpayGateway());
        razorpayProcessor.processPayment(50.00);
    }
}