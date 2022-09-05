package model;
import java.util.Date;
import java.util.HashMap;
import model.Box;
import model.Player;
import model.LinkedList;

public class Game {

    private double score;
    private Date date;
    private Player player;
    private HashMap<String, Box> board;

    public Game(Player player) {
        this.player = player;
        board = new HashMap<>();
    }

//    public void put(String key, Box value){
//        board.put(key, value);
//    }

    public void createBoxes(){
        createBoxes(1,1, null);
    }

    private void createBoxes(int x, int y, String key){

        key = x+","+y;
        board.put(key, new Box(" "));

        if(x == 8 && y == 8){
            return;
        }

        if(y%8 == 0){
            x = x+1;
            y = 1;
        }else{
            y = y+1;
        }

        createBoxes(x,y,key);
    }

    public void print(){
        System.out.println("\n    1    2    3    4    5    6    7    8");
        System.out.print(" "+1);
        print(1,1, null);
    }

    private void print(int x, int y, String key){

        key = x+","+y;
        System.out.print(board.get(key).toString());

        if(x == 8 && y == 8){
            System.out.println("\n");
            return;
        }

        if(y%8 == 0){
            x = x+1;
            y = 1;
            System.out.println();
            System.out.print(" "+x);
        }else{
            y = y+1;
        }

        print(x,y,key);
    }

}
