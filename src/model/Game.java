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

    public Game(Player player, HashMap<String, Box> board) {
        this.player = player;
        this.board = board;
    }

    public void put(String key, Box value){
        board.put(key, value);
    }

    public void createBoxes(){
        createBoxes(0,0, null);
    }

    private void createBoxes(int x, int y, String key){

        if(x == 8 && y == 8){
            return;
        }

        key = x+","+y;


        board.put(key, null);

    }

}
