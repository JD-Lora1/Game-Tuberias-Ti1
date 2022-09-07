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
        String randSource = rand1[0]+","+rand1[1];
        String randDrain = rand2[0]+","+rand2[1];

        createBoxes(key, "", randSource,randDrain);
    }

    private void createBoxes(int[] keyArray, String key, String randSource, String randDrain){
        key = keyArray[0]+","+keyArray[1];
        if(key.equals(randSource)){
            board.put(key, new Box(" F "));
            pipesList.setSource(board.get(key).getNode());

            //Set box key links for source
            boxKeyLinks(keyArray, board.get(key));
        }else if (key.equals(randDrain)){
            board.put(key, new Box(" D "));
            pipesList.setDrain(board.get(key).getNode());

            //Set box key links for drain
            boxKeyLinks(keyArray, board.get(key));
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
        createBoxes(keyArray, key,randSource,randDrain);
    }

    public void boxKeyLinks(int[] keyArray, Box current){
        String[] boxKeyLinks = new String[4];
        //clockwise direction. 0 up, 1 right, 2 down, 3 left
        boxKeyLinks[0] = keyArray[0]+","+(keyArray[1]-1);
        boxKeyLinks[0] = (keyArray[0]+1)+","+keyArray[1];
        boxKeyLinks[0] = keyArray[0]+","+keyArray[1];
        boxKeyLinks[3] = (keyArray[0]-1)+","+(keyArray[1]+1);

        current.setUp(board.get(boxKeyLinks[0]));
        current.setRight(board.get(boxKeyLinks[1]));
        current.setDown(board.get(boxKeyLinks[2]));
        current.setLeft(board.get(boxKeyLinks[3]));
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
    public void setBoxNodeType(String coordinate, String opt){
        /**
         * 1: =
         * 2: ||
         * 3: o
         * 4: Delete, break links, setType:X
         * */
        if(board.get(coordinate).getNode()==null){
            if (opt.equals("1")) {
                board.get(coordinate).setNode(new Node(" = "));
                pipesList.addLast(board.get(coordinate).getNode());
            }else if (opt.equals("2")) {
                board.get(coordinate).setNode(new Node("|| "));
                pipesList.addLast(board.get(coordinate).getNode());
            }else if (opt.equals("3")) {
                board.get(coordinate).setNode(new Node(" o "));
                pipesList.addLast(board.get(coordinate).getNode());
            }else if(opt.equals("4")){
                System.out.println("There is no pipe to delete");
            }
        } else if(!board.get(coordinate).getNode().getType().equals(" F ") && !board.get(coordinate).getNode().getType().equals(" D ")){
            if (opt.equals("1")) {
                board.get(coordinate).getNode().setType(" = ");
            }else if (opt.equals("2")) {
                board.get(coordinate).getNode().setType("|| ");
            }else if (opt.equals("3")) {
                board.get(coordinate).getNode().setType(" o ");
            }else if(opt.equals("4")){
                pipesList.delete(board.get(coordinate).getNode());
                board.get(coordinate).setNode(null);
                System.out.println("The pipe was deleted");
            }
        }else{
            System.out.println("You can't edit the Source or the Drain");
        }


    }
    //GET
    public HashMap<String, Box> getBoard() {
        return board;
    }
}
