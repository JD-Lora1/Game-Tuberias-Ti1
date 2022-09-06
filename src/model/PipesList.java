package model;

public class PipesList {

    private Box fountain; // Fuente F
    private Box drain; // Drenaje D
    private Box tail; //temporal tail

    public void addLast(Box box){
        if(tail == null){
            tail = box;
            fountain.setNext(tail);
            tail.setPrev(fountain);
        }else {
            tail.setNext(box);
            tail.getNext().setPrev(tail);
            tail = box;
        }
    }

}
