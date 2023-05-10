package Toys;

import java.io.Serializable;

public class Toy implements Serializable {
    private int id;
    private String name;
    private float percent;
    private static int count;

    public Toy(String name, float percent) {
        this.id = count;
        this.name = name;
        this.percent = percent;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercent() {
        return percent;
    }

    public static int getCount() {
        return count;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public Toy change(String name, float percent) {
        setName(name);
        setPercent(percent);
        return null;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + percent;
    }

    public void remove(){
        count--;
    }
}
