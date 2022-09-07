package model;
import java.util.HashMap;

public class Box {

    private String type;
    private Box next;
    private Box prev;
    private Box up;
    private Box down;
    private Box right;
    private Box left;

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

    public Box getUp() {
        return up;
    }

    public void setUp(Box up) {
        this.up = up;
    }

    public Box getDown() {
        return down;
    }

    public void setDown(Box down) {
        this.down = down;
    }

    public Box getRight() {
        return right;
    }

    public void setRight(Box right) {
        this.right = right;
    }

    public Box getLeft() {
        return left;
    }

    public void setLeft(Box left) {
        this.left = left;
    }
}
