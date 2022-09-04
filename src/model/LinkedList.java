package model;

public class LinkedList {

    private NodeLL drain;
    private NodeLL source;
    private NodeLL next;
    private NodeLL previous;

    public void addLast(NodeLL node){
        if(drain == null){
            drain = node;
            source = node;
        }else{
            source.setNext(node);
            source = node;
        }
    }

}
