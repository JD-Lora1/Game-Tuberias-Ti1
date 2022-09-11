package model;

public class NodeLL {
    private String type;
    private String coordinate;
    private NodeLL next;
    private NodeLL prev;

    public NodeLL(String type, String coordinate) {
        this.type = type;
        this.coordinate = coordinate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NodeLL getNext() {
        return next;
    }

    public void setNext(NodeLL next) {
        this.next = next;
    }

    public NodeLL getPrev() {
        return prev;
    }

    public void setPrev(NodeLL prev) {
        this.prev = prev;
    }

    public String getCoordinate() {
        return coordinate;
    }

}
