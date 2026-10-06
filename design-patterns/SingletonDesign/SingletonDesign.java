
class Singleton {
    private Singleton() {
        System.out.println("Singleton Constructor called, new objecty is created");
    }

    private static volatile Singleton singletonInstance;

    public static Singleton getInstance() {
        if (singletonInstance == null) {  //first check for the locking
            synchronized (Singleton.class) {
                if (singletonInstance == null) { // second check if the object is already created
                    return singletonInstance = new Singleton();
                }
            }
        }

        return singletonInstance;
    }
}

public class SingletonDesign {

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2);
    }

}
