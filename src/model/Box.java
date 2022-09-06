package model;
import java.util.HashMap;

public class Box {

    private String type;
    private Box next;
    private Box prev;

    public Box(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return " "+type+" ";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Box getNext() {
        return next;
    }

    public void setNext(Box next) {
        this.next = next;
    }

    public Box getPrev() {
        return prev;
    }

    public void setPrev(Box prev) {
        this.prev = prev;
    }
}
