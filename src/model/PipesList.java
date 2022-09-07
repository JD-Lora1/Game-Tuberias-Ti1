package model;

public class PipesList {

    private Node source; // Fuente F
    private Node drain; // Drenaje D
    private Node tail; //temporal tail

    public void addLast(Node node){
        if(tail == null){
            tail = node;
            source.setNext(tail);
            tail.setPrev(source);
        }else {
            tail.setNext(node);
            tail.getNext().setPrev(tail);
            tail = node;
        }
    }

    public void delete(Node toDelete/*, Node current*/){
        tail = toDelete.getPrev();
        tail.setNext(null);
        toDelete.setPrev(null);
        if (drain.getPrev()!= null){
            drain.setPrev(null);
        }
    }

    //GET and SET

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getDrain() {
        return drain;
    }

    public void setDrain(Node drain) {
        this.drain = drain;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
}
