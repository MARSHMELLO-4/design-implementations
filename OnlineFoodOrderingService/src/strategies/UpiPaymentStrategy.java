package strategies;

public class UpiPaymentStrategy implements PaymentStrategy {
    private String mobile;

    public UpiPaymentStrategy(String mob){
        this.mobile = mob;
    }

@Override
public void pay(double amount) {
    // TODO Auto-generated method stub
    System.out.println("Payment paid using UPI");
}

}