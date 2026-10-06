package strategies;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPaymentStrategy(String card){
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        // TODO Auto-generated method stub
        System.out.println("Amount paid using card");
    }


}
