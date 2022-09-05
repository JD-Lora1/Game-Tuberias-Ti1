package model;
import java.util.HashMap;

public class Box {

    private String type;

    public Box(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return " ["+type+"] ";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
