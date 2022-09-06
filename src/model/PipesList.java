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

    public Box getFountain() {
        return fountain;
    }

    public void setFountain(Box fountain) {
        this.fountain = fountain;
    }

    public Box getDrain() {
        return drain;
    }

    public void setDrain(Box drain) {
        this.drain = drain;
    }

    public Box getTail() {
        return tail;
    }

    public void setTail(Box tail) {
        this.tail = tail;
    }
}
