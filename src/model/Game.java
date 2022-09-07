package model;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private double score;
    private Date date;
    private Player player;
    private HashMap<String, Box> board;
    private PipesList pipesList;
    private Random random = new Random();
    private int[] rand1 = new int[2];
    private int[] rand2 = new int[2];

    public Game(Player player) {
        this.player = player;
        board = new HashMap<>();
        pipesList = new PipesList();
    }

    public void createBoxes(){
        int[] key = new int[2];
        key[0]=0;
        key[1]=0;
        rand1[0] = random.nextInt(8);
        rand1[1] = random.nextInt(8);
        rand2[0] = random.nextInt(8);
        rand2[1] = random.nextInt(8);
        while (rand1[0]==rand2[0] && rand1[1]==rand2[1]){
            rand2[0] = random.nextInt(8);
            rand2[1] = random.nextInt(8);
        }
        String randFountain = rand1[0]+","+rand1[1];
        String randDrain = rand2[0]+","+rand2[1];

        createBoxes(key, "", randFountain,randDrain);
    }

    private void createBoxes(int[] keyArray, String key, String randFountain, String randDrain){
        key = keyArray[0]+","+keyArray[1];
        if(key.equals(randFountain)){
            board.put(key, new Box(" F "));
            pipesList.setSource(board.get(key));
        }else if (key.equals(randDrain)){
            board.put(key, new Box(" D "));
            pipesList.setDrain(board.get(key));
        }else {
            board.put(key, new Box(" x "));
        }

        if(keyArray[0] == 7 && keyArray[1] == 7){
            return;
        }

        if(keyArray[1]%7==0 && keyArray[1]!=0){
            keyArray[0] = keyArray[0]+1;
            keyArray[1] = 0;
        }else{
            keyArray[1] = keyArray[1]+1;
        }
        createBoxes(keyArray, key,randFountain,randDrain);
    }

    public void print(){
        System.out.println("\n    0    1    2    3    4    5    6    7");
        System.out.print(" "+0);
        int[] key = new int[2];
        key[0]=0;
        key[1]=0;
        print(key, "");
    }

    private void print(int[] keyArray, String key){
        key = keyArray[0]+","+keyArray[1];
        System.out.print(board.get(key).toString());

        if(keyArray[0] == 7 && keyArray[1] == 7){
            System.out.println("\n");
            return;
        }
        if(keyArray[1]%7 == 0 && keyArray[1]!=0){
            keyArray[0] = keyArray[0]+1;
            keyArray[1] = 0;
            System.out.println();
            System.out.print(" "+keyArray[0]);
        }else{
            keyArray[1] = keyArray[1]+1;
        }

        print(keyArray, key);
    }

    //To select a box writing its coordinate by keyboard, and set its type
    public void selectBox(String coordinate){
        String[] arrCoordinate = coordinate.split(",");
        int[] intCoordinate = new int[2];
        intCoordinate[0] = Integer.parseInt(arrCoordinate[0]);
        intCoordinate[1] = Integer.parseInt(arrCoordinate[1]);

        //to
        //String finalCoordinate
        pipesList.getSource();
    }

    public void setBoxType(String coordinate, String opt){
        /**
         * 1: =
         * 2: ||
         * 3: o
         * 4: Delete, break links, setType:X
         * */
        if (opt.equals("1")) {
            System.out.println("opt 1");
            board.get(coordinate).setType(" = ");
        }else if (opt.equals("2")) {
            System.out.println("opt 2");
            board.get(coordinate).setType("|| ");
        }else if (opt.equals("3")) {
            System.out.println("opt 3");
            board.get(coordinate).setType(" o ");
        }else if(opt.equals("4")){
            System.out.println("opt 4");
            board.get(coordinate).setType(" x ");
            System.out.println("The pipe was deleted");
            //Delete node, break links
        }
    }
    //GET
    public HashMap<String, Box> getBoard() {
        return board;
    }
}
