package model;

public class NodeLL {

    //Values
    private Box box;

    //Enlace
    private NodeLL next;


    public NodeLL(Box box){
        this.box = box;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public NodeLL getNext() {
        return next;
    }

    public void setNext(NodeLL next) {
        this.next = next;
    }

}
