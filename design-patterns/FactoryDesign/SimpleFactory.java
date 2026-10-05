package FactoryDesign;

interface Burger {
    void prepare();
}

class SimpleBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Simple Burger is prepared");
    }
}


class StandardBurger implements  Burger {
    @Override 
    public void prepare() {
        System.out.println("Standard Burger Created");
    }
}

class PremiumBurger implements  Burger {
    @Override 
    public void prepare (){
        System.out.println("Premium Burger Created");
    }
}


class BurgerFactory {

    public Burger createBurger(String type){
        if(type == "basic"){
            return new SimpleBurger();
        } else if(type == "standard"){
            return new StandardBurger();
        } else if(type == "premium"){
            return new PremiumBurger();
        } else{
            System.out.println("Invalid type of the burger selected " + type);
            throw new NullPointerException();
        }
    }

}

public class SimpleFactory {
    public static void main(String[] args) {
        //here we can create the objects 
        BurgerFactory burgerFactory = new BurgerFactory();

        Burger simpleBurger = burgerFactory.createBurger("basic");
        Burger standardBurger = burgerFactory.createBurger("standard");
        Burger premiBurger = burgerFactory.createBurger("premium");

        simpleBurger.prepare();
        standardBurger.prepare();
        premiBurger.prepare();

    }
}
