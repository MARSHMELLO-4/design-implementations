package models;

public class DeliveryOrder extends Order {

    private String userAddress;

    public DeliveryOrder(){
        userAddress = "";
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Delivery";
    }

    public void setUserAddress(String addr) {
        userAddress = addr;
    }

    public String getUserAddress() {
        return userAddress;
    }

    
}
