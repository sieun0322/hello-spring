package hello.core.singleton;

/**
 * Singleton 방식의 주의점
 */
public class StatefulService {
    //private int price;

    public int order(String name, int price){
        System.out.println("name = " + name + "price = "+price);
        return price;
    }

    /*
    public int getPrice() {
        //return price;
    }
    */
}
