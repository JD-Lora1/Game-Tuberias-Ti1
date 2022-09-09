package model;

public class PipesList {

    private NodeLL source; // Fuente F
    private NodeLL drain; // Drenaje D
    private NodeLL tail; //temporal tail

    public void addLast(NodeLL nodeLL){
        if(tail == null){
            tail = nodeLL;
            source.setNext(tail);
            tail.setPrev(source);
        }else {
            //TODO revisar ir works
            tail.setNext(nodeLL);
            tail.getNext().setPrev(tail);
            tail = nodeLL;
        }
    }

    public void delete(NodeLL toDelete){
        tail = toDelete.getPrev();
        tail.setNext(null);
        toDelete.setPrev(null);
        if (drain.getPrev()!= null){
            drain.setPrev(null);
        }
    }

    //GET and SET

    public NodeLL getSource() {
        return source;
    }

    public void setSource(NodeLL source) {
        this.source = source;
    }

    public NodeLL getDrain() {
        return drain;
    }

    public void setDrain(NodeLL drain) {
        this.drain = drain;
    }

    public NodeLL getTail() {
        return tail;
    }

    public void setTail(NodeLL tail) {
        this.tail = tail;
    }
}
