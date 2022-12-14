package model;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private final Player player;
    private HashMap<String, Box> board;
    private PipesList pipesList;
    private Random random = new Random();
    private final int[] rand1 = new int[2];
    private final int[] rand2 = new int[2];
    private String randSource = "";
    private String randDrain = "";
    private String currentTail = "";
    private static final String HORIZONTAL_P = " = ";
    private static final String VERTICAL_P = "|| ";
    private static final String CIRCULAR_P = " o ";
    private int numPipes = 0;
    private double score;
    private long initialTime;
    private long finalTime;
    private long scoreTime;

    public Game(Player player) {
        this.player = player;
        board = new HashMap<>();
        pipesList = new PipesList();
        numPipes = 0;
    }

    public void createBoxes() {
        int[] key = new int[2];
        rand1[0] = random.nextInt(8);
        rand1[1] = random.nextInt(8);
        rand2[0] = random.nextInt(8);
        rand2[1] = random.nextInt(8);
        while (rand1[0] == rand2[0] && rand1[1] == rand2[1]) {
            rand2[0] = random.nextInt(8);
            rand2[1] = random.nextInt(8);
        }
        randSource = rand1[0] + "," + rand1[1];
        randDrain = rand2[0] + "," + rand2[1];
        currentTail = randSource;

        createBoxes(key, "");
        initialTime = System.currentTimeMillis();
    }

    private void createBoxes(int[] keyArray, String key) {
        key = keyArray[0] + "," + keyArray[1];
        if (key.equals(randSource)) {
            board.put(key, new Box(" F ",randSource));
            pipesList.setSource(board.get(key).getNodeLL());
            pipesList.setTail(board.get(key).getNodeLL());

            //Set box key links for source
            boxKeyLinks(key, board.get(key));
        } else if (key.equals(randDrain)) {
            board.put(key, new Box(" D ",randDrain));
            pipesList.setDrain(board.get(key).getNodeLL());

            //Set box key links for drain
            boxKeyLinks(key, board.get(key));
        } else {
            board.put(key, new Box(" x ",key));
        }

        if (keyArray[0] == 7 && keyArray[1] == 7) {
            return;
        }

        if (keyArray[1] % 7 == 0 && keyArray[1] != 0) {
            keyArray[0] = keyArray[0] + 1;
            keyArray[1] = 0;
        } else {
            keyArray[1] = keyArray[1] + 1;
        }
        createBoxes(keyArray, key);
    }


    private void boxKeyLinks(String key, Box current) {
        String[] keyStr = key.split(",");
        int[] keyArray = new int[2];
        keyArray[0] = Integer.parseInt(keyStr[0]);
        keyArray[1] = Integer.parseInt(keyStr[1]);

        String[] boxKeyLinks = new String[4];
        //clockwise direction. 0 up, 1 right, 2 down, 3 left
        boxKeyLinks[0] = (keyArray[0] - 1) + "," + (keyArray[1]);
        boxKeyLinks[1] = keyArray[0] + "," + (keyArray[1] + 1);
        boxKeyLinks[2] = (keyArray[0] + 1) + "," + (keyArray[1]);
        boxKeyLinks[3] = keyArray[0] + "," + (keyArray[1] - 1);

        current.setUp(board.get(boxKeyLinks[0]));
        current.setRight(board.get(boxKeyLinks[1]));
        current.setDown(board.get(boxKeyLinks[2]));
        current.setLeft(board.get(boxKeyLinks[3]));
    }

    public void print() {
        System.out.println("\n    0    1    2    3    4    5    6    7");
        System.out.print(" " + 0);
        int[] key = new int[2];
        print(key, "");
    }

    private void print(int[] keyArray, String key) {
        key = keyArray[0] + "," + keyArray[1];
        System.out.print(board.get(key).toString());

        if (keyArray[0] == 7 && keyArray[1] == 7) {
            System.out.println("\n");
            return;
        }
        if (keyArray[1] % 7 == 0 && keyArray[1] != 0) {
            keyArray[0] = keyArray[0] + 1;
            keyArray[1] = 0;
            System.out.print("\n " + keyArray[0]);
        } else {
            keyArray[1] = keyArray[1] + 1;
        }

        print(keyArray, key);
    }
    //Flow
    public String waterFlow(String opt){
        boolean breakCondition = true;
        if(board.get(currentTail).getLeft()!=null){
            if(board.get(currentTail).getNodeLL().getType().equals(HORIZONTAL_P)
                    && board.get(currentTail).getLeft()==board.get(randDrain)) {
                breakCondition = setDrainAsTail();
            }
        }
        if(board.get(currentTail).getRight()!=null && breakCondition){
            if(board.get(currentTail).getNodeLL().getType().equals(HORIZONTAL_P)
                    && board.get(currentTail).getRight()==board.get(randDrain)){
                breakCondition = setDrainAsTail();
            }

        }
        if(board.get(currentTail).getUp()!=null && breakCondition){
            if(board.get(currentTail).getNodeLL().getType().equals(VERTICAL_P)
                    && board.get(currentTail).getUp()==board.get(randDrain)){
                breakCondition = setDrainAsTail();
            }
        }
        if (board.get(currentTail).getDown()!=null && breakCondition) {
            if(board.get(currentTail).getNodeLL().getType().equals(VERTICAL_P)
                    && board.get(currentTail).getDown()==board.get(randDrain)){
                setDrainAsTail();
            }
        }
        //WaterFlow on pipelist
        if(pipesList.waterFlow()){
            finalTime = System.currentTimeMillis();
            scoreTime = (finalTime-initialTime)/1000;
            //System.out.println("Tiempo en segundos: "+scoreTime+"/ Tuberias usadas: "+numPipes);
            System.out.println("The Water's Flow is Great. You won!!\nYour score: "+getScore()+"\n");
            opt = "3";
        }else {
            System.out.println("Seems there is something wrong with the pipes :(.\nKeep Trying\n");
        }
        return opt;
    }

    public boolean setDrainAsTail(){
        pipesList.getDrain().setPrev(pipesList.getTail());
        pipesList.getTail().setNext(pipesList.getDrain());
        pipesList.setTail(pipesList.getDrain());
        currentTail = randDrain;
        return false;
    }

    public void setBoxNodeType(String coordinate, String opt) {
        //set up, right, donw, left links
        String key = currentTail;
        boxKeyLinks(key, board.get(currentTail));
        /**
         * 1: =
         * 2: ||
         * 3: o
         * 4: Delete, break links, setType:X
         * */
        if (board.get(coordinate).getNodeLL() == null) {
            if (board.get(currentTail).getRight() == board.get(coordinate) || board.get(currentTail).getLeft() == board.get(coordinate)) {
                boolean condition = false;
                // source, "o", "||", "="
                if(board.get(randSource).getRight()==board.get(coordinate) || board.get(randSource).getLeft()==board.get(coordinate)){
                    condition = true;
                }
                else if (pipesList.getTail().getType().equals(CIRCULAR_P)) {
                    if(board.get(currentTail).getDown()!=null){
                        if(board.get(currentTail).getDown().getNodeLL()!=null){
                            condition = board.get(currentTail).getDown().getNodeLL().getType().equals(VERTICAL_P);
                        }
                    }
                    if(board.get(currentTail).getUp()!=null){
                        if(board.get(currentTail).getUp().getNodeLL()!=null){
                            condition = board.get(currentTail).getUp().getNodeLL().getType().equals(VERTICAL_P);
                        }
                    }
                }
                else if (pipesList.getTail().getType().equals(VERTICAL_P)) {
                    System.out.println("You can't put any type of pipe here. There is a vertical pipe previosly");
                } else if (pipesList.getTail().getType().equals(HORIZONTAL_P)) {
                    if (opt.equals("1")) {
                        setNodeType(key,coordinate, HORIZONTAL_P);
                    } else if (opt.equals("2")) {
                        System.out.println("You can't put this type of pipe here");
                    } else if (opt.equals("3")) {
                        setNodeType(key,coordinate, CIRCULAR_P);
                    } else if (opt.equals("4")) {
                        System.out.println("There is no pipe to delete");
                    }
                }
                //for " o " and "F"
                if (condition) {
                    if (opt.equals("1")) {
                        setNodeType(key,coordinate, HORIZONTAL_P);
                    } else if (opt.equals("2") || opt.equals("3")) {
                        System.out.println("You can't put this type of pipe here");
                    } else if (opt.equals("4")) {
                        System.out.println("There is no pipe to delete");
                    }
                }
            }
            else if (board.get(currentTail).getDown() == board.get(coordinate) || board.get(currentTail).getUp() == board.get(coordinate)) {
                boolean condition = false;
                // source, "o", "||", "="
                if(board.get(randSource).getDown()==board.get(coordinate) || board.get(randSource).getUp()==board.get(coordinate)){
                    condition = true;
                }
                else if (pipesList.getTail().getType().equals(CIRCULAR_P)) {
                    if(board.get(currentTail).getRight()!=null){
                        if(board.get(currentTail).getRight().getNodeLL()!=null){
                            condition = board.get(currentTail).getRight().getNodeLL().getType().equals(HORIZONTAL_P);
                        }
                    }
                    if(board.get(currentTail).getLeft()!=null){
                        if(board.get(currentTail).getLeft().getNodeLL()!=null){
                            condition = board.get(currentTail).getLeft().getNodeLL().getType().equals(HORIZONTAL_P);
                        }
                    }
                } else if (pipesList.getTail().getType().equals(HORIZONTAL_P)) {
                    System.out.println("You can't put any type of pipe here. There is a vertical pipe previosly");
                } else if (pipesList.getTail().getType().equals(VERTICAL_P)) {
                    if (opt.equals("1")) {
                        System.out.println("You can't put this type of pipe here");
                    } else if (opt.equals("2")) {
                        setNodeType(key,coordinate, VERTICAL_P);
                    } else if (opt.equals("3")) {
                        setNodeType(key,coordinate, CIRCULAR_P);
                    } else if (opt.equals("4")) {
                        System.out.println("There is no pipe to delete");
                    }
                }
                // final
                if (condition) {
                    if (opt.equals("1") || opt.equals("3")) {
                        System.out.println("You can't put this type of pipe here");
                    } else if (opt.equals("2")) {
                        setNodeType(key,coordinate, VERTICAL_P);
                    } else if (opt.equals("4")) {
                        System.out.println("There is no pipe to delete");
                    }
                }
            }else {
                System.out.println("You should continue connecting pipes to the Tail\n" +
                        "If there are no pipes, the tail is the Source (F)");
            }
        }else {
            setBoxFilledNodeType(coordinate, opt);
        }
        boxKeyLinks(currentTail, board.get(currentTail));
    }

    public void setBoxFilledNodeType(String coordinate, String opt){
        if (opt.equals("4")){
            pipesList.delete(board.get(coordinate).getNodeLL());
            for (int i = 0; i<pipesList.getCoordinatesToDelete().size();i++){
                board.get(pipesList.getCoordinatesToDelete().get(i)).setNodeLL(null);
                numPipes--;
            }
            actualizeCurrentTail();
            System.out.println("The pipes were deleted");
        }else {
            System.out.println("You can't edit this pipe\n" +
                    "If you wanna change it, first delete it (to delete all of his nexts pipes)");
        }
    }

    public void actualizeCurrentTail(){
        currentTail = pipesList.getTail().getCoordinate();
    }

    public void setNodeType(String key, String coordinate, String type){
        boxKeyLinks(key, board.get(coordinate));
        board.get(coordinate).setNodeLL(new NodeLL(type,coordinate));
        pipesList.addLast(board.get(coordinate).getNodeLL());
        pipesList.setTail(board.get(coordinate).getNodeLL());
        actualizeCurrentTail();
        numPipes+=1;
    }
    public HashMap<String, Box> getBoard() {
        return board;
    }

    public double getScore(){
        score = numPipes*100 - ((60-scoreTime)*10);
        //We should use absolute value of ((60-scoreTime)*10)
        return score;
    }

    public String getPlayerName(){
        return player.getName();
    }


}
