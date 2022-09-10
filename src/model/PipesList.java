package model;

import java.util.ArrayList;

public class PipesList {

    private NodeLL source; // Fuente F
    private NodeLL drain; // Drenaje D
    private NodeLL tail; // Temporal tail
    private ArrayList<String> coordinatesToDelete = new ArrayList<>();

    public void addLast(NodeLL nodeLL){
        if(tail == source){
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

    public void delete(NodeLL current){
        tail = current.getPrev();
        coordinatesToDelete.clear();
        delete(current,null);
    }
    private void delete(NodeLL current, NodeLL currentNext){
        if(current==null || current==drain){
            return;
        }
        coordinatesToDelete.add(current.getCoordinate());
        currentNext = current.getNext();
        current.setNext(null);
        current.setPrev(null);

        delete(currentNext, currentNext);
    }

    public boolean waterFlow(){
        return waterFlow(source);
    }

    private boolean waterFlow(NodeLL current){
        if (current.getNext() == null)
            return false;
        if (current.getNext() == getDrain())
            return true;

        return waterFlow(current.getNext());
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

    public ArrayList<String> getCoordinatesToDelete() {
        return coordinatesToDelete;
    }
}
