package model;
import java.util.HashMap;

public class Box {

    private Node node;
    private Box up;
    private Box down;
    private Box right;
    private Box left;

    public Box(String type) {
        if(type.equals(" F ") || type.equals(" D ")){
            node = new Node(type);
        }
    }

    @Override
    public String toString() {
        if (node!=null){
            return " "+node.getType()+" ";
        } else {
            return "  x  ";
        }
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
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
