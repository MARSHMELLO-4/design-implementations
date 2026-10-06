package models;

public class User {
    private int userId;
    private String name;
    private String addr;
    private Cart cart;

    public User(int userId, String name, String address){
        this.userId = userId;
        this.name = name;
        this.addr = address;
        this.cart = new Cart();
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String a) {
        address = a;
    }

    public Cart getCart() {
        return cart;
    }
}
