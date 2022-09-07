package model;

public class Box {

    private NodeLL nodeLL;
    private Box up;
    private Box down;
    private Box right;
    private Box left;

    public Box(String type) {
        if(type.equals(" F ") || type.equals(" D ")){
            nodeLL = new NodeLL(type);
        }
    }

    @Override
    public String toString() {
        if (nodeLL !=null){
            return " "+ nodeLL.getType()+" ";
        } else {
            return "  x  ";
        }
    }

    public NodeLL getNode() {
        return nodeLL;
    }

    public void setNode(NodeLL nodeLL) {
        this.nodeLL = nodeLL;
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
