package model;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import model.Box;
import model.Player;
import model.PipesList;

public class Game {

    private double score;
    private Date date;
    private Player player;
    private HashMap<String, Box> board;
    private Random random = new Random();
    private int[] rand1 = new int[2];
    private int[] rand2 = new int[2];

    public Game(Player player) {
        this.player = player;
        board = new HashMap<>();
    }

    public void createBoxes(){
        int[] key = new int[2];
        key[0]=1;
        key[1]=1;
        rand1[0] = random.nextInt(8)+1;
        rand1[1] = random.nextInt(8)+1;
        rand2[0] = random.nextInt(8)+1;
        rand2[1] = random.nextInt(8)+1;
        String randFountain = rand1[0]+","+rand1[0];
        String randDrain = rand2[0]+","+rand2[0];

        createBoxes(key, "", randFountain,randDrain);
    }

    private void createBoxes(int[] keyArray, String key, String randFountain, String randDrain){
        key = keyArray[0]+","+keyArray[1];
        if(key.equals(randFountain)){
            board.put(key, new Box("F"));
        }else if (key.equals(randDrain)){
            board.put(key, new Box("D"));
        }else {
            board.put(key, new Box("x"));
        }

        if(keyArray[0] == 8 && keyArray[1] == 8){
            return;
        }

        if(keyArray[1]%8 == 0){
            keyArray[0] = keyArray[0]+1;
            keyArray[1] = 1;
        }else{
            keyArray[1] = keyArray[1]+1;
        }

        createBoxes(keyArray, key,randFountain,randDrain);
    }

    public void print(){
        System.out.println("\n    1    2    3    4    5    6    7    8");
        System.out.print(" "+1);
        int[] key = new int[2];
        key[0]=1;
        key[1]=1;
        print(key, "");
    }

    private void print(int[] keyArray, String key){
        key = keyArray[0]+","+keyArray[1];
        System.out.print(board.get(key).toString());

        if(keyArray[0] == 8 && keyArray[1] == 8){
            System.out.println("\n");
            return;
        }
        if(keyArray[1]%8 == 0){
            keyArray[0] = keyArray[0]+1;
            keyArray[1] = 1;
            System.out.println();
            System.out.print(" "+keyArray[0]);
        }else{
            keyArray[1] = keyArray[1]+1;
        }

        print(keyArray, key);
    }

}
