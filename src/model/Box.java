package model;
import java.util.HashMap;

public class Box {

    private String coordinate;
    private String type;

    public Box(String coordinate, String type) {
        this.coordinate = coordinate;
        this.type = type;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
