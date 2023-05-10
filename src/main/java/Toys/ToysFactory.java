package Toys;

public class ToysFactory {
    public Toy createToy(ToysType toysType, String name,float percent) {
        Toy toy = null;
        switch (toysType) {
            case CAR -> toy = new Car(name, percent);
            case BOAT -> toy = new Boat(name, percent);
            case DOLL -> toy = new Doll(name, percent);
            case PLAIN -> toy = new Plain(name, percent);
            case TRAIN -> toy = new Train(name, percent);
        }
        return toy;
    }
}
