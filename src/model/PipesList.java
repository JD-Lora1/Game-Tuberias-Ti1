package model;

public class PipesList {

    private Box source; // Fuente F
    private Box drain; // Drenaje D
    private Box tail; //temporal tail
    private Box up;
    private Box down;
    private Box right;
    private Box left;

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
    public void delete(Box toDelete){
        //No se puede eliminar fuente o drenaje, ni editarlas
        if(source == toDelete || drain == toDelete){
            System.out.println("You can't edit the Source or Drain");
            return;
        }else {

        }
        /*if(tail.getValue() == key && tail == current){
            previous.setNext(null);
            tail = previous;
            return;
        }
        if(current.getValue() == key){
            previous.setNext(current.getNext());
            return;
        }
        //Llamado recursivo
        delete(key, current, current.getNext());*/
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
