package models;

public class PickupOrder extends Order {

    private String restaurantAddr;

    public PickupOrder(){
        restaurantAddr = "";
    }


    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Pickup";
    }

    public void setRestaurantAddress(String addr) {
        restaurantAddress = addr;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }
    
    
}
