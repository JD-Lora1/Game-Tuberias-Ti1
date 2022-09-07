package model;

public class PipesList {

    private Box source; // Fuente F
    private Box drain; // Drenaje D
    private Box tail; //temporal tail

    public void addLast(Box box){
        if(tail == null){
            tail = box;
            source.setNext(tail);
            tail.setPrev(source);
        }else {
            tail.setNext(box);
            tail.getNext().setPrev(tail);
            tail = box;
        }
    }
    public void delete(Box toDelete, Box current){
        //No se puede eliminar fuente o drenaje, ni editarlas
        if(source == toDelete || drain == toDelete){
            System.out.println("You can't edit the Source or Drain");
            return;
        }else if(current == toDelete){
            tail = current.getPrev();
            tail.setNext(null);
            current.setPrev(null);
            return;
        }else {
            tail = current.getPrev();
            tail.setNext(null);
            current.setPrev(null);
            delete(toDelete, current);
        }
    }

    //GET and SET

    public Box getSource() {
        return source;
    }

    public void setSource(Box source) {
        this.source = source;
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
