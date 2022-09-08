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
    private String randSource = "";
    private String randDrain = "";
    private String currentTail = "";
    private int numPipes = 0;

    public Game(Player player) {
        this.player = player;
        board = new HashMap<>();
        pipesList = new PipesList();
    }

    public void createBoxes() {
        int[] key = new int[2];
        key[0] = 0;
        key[1] = 0;
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

        createBoxes(key, "", randSource, randDrain);
    }

    private void createBoxes(int[] keyArray, String key, String randSource, String randDrain) {
        this.randSource = randSource;
        this.randDrain = randDrain;
        key = keyArray[0] + "," + keyArray[1];
        if (key.equals(randSource)) {
            board.put(key, new Box(" F "));
            pipesList.setSource(board.get(key).getNode());

            //Set box key links for source
            boxKeyLinks(keyArray, board.get(key));
        } else if (key.equals(randDrain)) {
            board.put(key, new Box(" D "));
            pipesList.setDrain(board.get(key).getNode());

            //Set box key links for drain
            boxKeyLinks(keyArray, board.get(key));
        } else {
            board.put(key, new Box(" x "));
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
        createBoxes(keyArray, key, randSource, randDrain);
    }

    public void boxKeyLinks(int[] keyArray, Box current) {
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
        System.out.println( current.getUp());
        System.out.println( current.getRight());
        System.out.println( current.getDown());
        System.out.println( current.getLeft());
    }

    public void print() {
        System.out.println("\n    0    1    2    3    4    5    6    7");
        System.out.print(" " + 0);
        int[] key = new int[2];
        key[0] = 0;
        key[1] = 0;
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
            System.out.println();
            System.out.print(" " + keyArray[0]);
        } else {
            keyArray[1] = keyArray[1] + 1;
        }

        print(keyArray, key);
    }
    //WaterFlow
    public void waterFlow(){
        boolean condition = false;
        if(board.get(randDrain).getLeft()!=null){
            condition = board.get(randDrain).getLeft().equals(" = ");
        }else if(board.get(randDrain).getRight()!=null){
            condition = board.get(randDrain).getRight().equals(" = ");
        }else if(board.get(randDrain).getUp()!=null){
            condition = board.get(randDrain).getUp().equals("|| ");
        } else if (board.get(randDrain).getDown()!=null) {
            condition = board.get(randDrain).getUp().equals("|| ");
        }
        if(condition){
            //boxKeyLinks(key, board.get(currentTail));
            board.get(currentTail).getNode().setNext(pipesList.getDrain());
            pipesList.setTail(pipesList.getDrain());
        }
        if(pipesList.getTail()==pipesList.getDrain()){
            System.out.println("Ganaste");
        }else {
            System.out.println("Perdiste");
        }
    }

    //To select a box writing its coordinate by keyboard, and set its type
    public void setBoxNodeType(String coordinate, String opt) {
        //set up, right, donw, left links
        int[] key = new int[2];
        String[] keyStr = currentTail.split(",");
        key[0] = Integer.parseInt(keyStr[0]);
        key[1] = Integer.parseInt(keyStr[1]);
        boxKeyLinks(key, board.get(currentTail));
        System.out.println("//"+currentTail);
        /**
         * 1: =
         * 2: ||
         * 3: o
         * 4: Delete, break links, setType:X
         * */
        if (board.get(coordinate).getNode() == null) {
            //up Down, Source
            if (board.get(randSource).getUp() == board.get(coordinate) || board.get(randSource).getDown() == board.get(coordinate)) {
                if (opt.equals("1") || opt.equals("3")) {
                    System.out.println("You can't put this type of pipe here");
                } else if (opt.equals("2")) {
                    currentTail = coordinate;
                    boxKeyLinks(key, board.get(currentTail));
                    board.get(coordinate).setNode(new NodeLL("|| "));
                    pipesList.addLast(board.get(coordinate).getNode());
                    numPipes+=1;
                } else if (opt.equals("4")) {
                    System.out.println("There is no pipe to delete");
                }
            }
            //Right Left, Source
            else if (board.get(randSource).getRight() == board.get(coordinate) || board.get(randSource).getLeft() == board.get(coordinate)) {
                if (opt.equals("1")) {
                    currentTail = coordinate;
                    boxKeyLinks(key, board.get(currentTail));
                    board.get(coordinate).setNode(new NodeLL(" = "));
                    pipesList.addLast(board.get(coordinate).getNode());
                    numPipes+=1;
                } else if (opt.equals("2") || opt.equals("3")) {
                    System.out.println("You can't put this type of pipe here");
                } else if (opt.equals("4")) {
                    System.out.println("There is no pipe to delete");
                }

            }
            //Another position != surce nexts for Left and Right
            else if (board.get(currentTail).getRight() == board.get(coordinate) || board.get(currentTail).getLeft() == board.get(coordinate)) {
                if (pipesList.getTail().getType().equals(" o ")) {
                    boolean condition;
                    if(board.get(currentTail).getDown()==null){
                        condition = board.get(currentTail).getUp().getNode().getType().equals("|| ");
                    }else {
                        condition = board.get(currentTail).getDown().getNode().getType().equals("|| ");
                    }
                    if (condition) {
                        if (opt.equals("1")) {
                            currentTail = coordinate;
                            boxKeyLinks(key, board.get(currentTail));
                            board.get(coordinate).setNode(new NodeLL(" = "));
                            pipesList.addLast(board.get(coordinate).getNode());
                            numPipes+=1;
                        } else if (opt.equals("2") || opt.equals("3")) {
                            System.out.println("You can't put this type of pipe here");
                        } else if (opt.equals("4")) {
                            System.out.println("There is no pipe to delete");
                        }
                    }
                } else if (pipesList.getTail().getType().equals("|| ")) {
                    System.out.println("You can't put any type of pipe here. There is a vertical pipe previosly");
                } else if (pipesList.getTail().getType().equals(" = ")) {
                    if (opt.equals("1")) {
                        currentTail = coordinate;
                        boxKeyLinks(key, board.get(currentTail));
                        board.get(coordinate).setNode(new NodeLL(" = "));
                        pipesList.addLast(board.get(coordinate).getNode());
                        numPipes += 1;
                    } else if (opt.equals("2")) {
                        System.out.println("You can't put this type of pipe here");
                    } else if (opt.equals("3")) {
                        currentTail = coordinate;
                        boxKeyLinks(key, board.get(currentTail));
                        board.get(coordinate).setNode(new NodeLL(" o "));
                        pipesList.addLast(board.get(coordinate).getNode());
                        numPipes += 1;
                    } else if (opt.equals("4")) {
                        System.out.println("There is no pipe to delete");
                    }
                }
            }
            //Another position != source nexts for Down and Up
            else if (board.get(currentTail).getDown() == board.get(coordinate) || board.get(currentTail).getUp() == board.get(coordinate)) {
                if (pipesList.getTail().getType().equals(" o ")) {
                    boolean condition;
                    if(board.get(currentTail).getRight()==null){
                        condition = board.get(currentTail).getLeft().getNode().getType().equals(" = ");
                    }else {
                        condition = board.get(currentTail).getRight().getNode().getType().equals(" = ");
                    }
                    if (condition) {
                        if (opt.equals("1") || opt.equals("3")) {
                            System.out.println("You can't put this type of pipe here");
                        } else if (opt.equals("2")) {
                            currentTail = coordinate;
                            boxKeyLinks(key, board.get(currentTail));
                            board.get(coordinate).setNode(new NodeLL("|| "));
                            pipesList.addLast(board.get(coordinate).getNode());
                            numPipes+=1;
                        } else if (opt.equals("4")) {
                            System.out.println("There is no pipe to delete");
                        }
                    }
                } else if (pipesList.getTail().getType().equals(" = ")) {
                    System.out.println("You can't put any type of pipe here. There is a vertical pipe previosly");
                } else if (pipesList.getTail().getType().equals("|| ")) {
                    if (opt.equals("1")) {
                        System.out.println("You can't put this type of pipe here");
                    } else if (opt.equals("2")) {
                        currentTail = coordinate;
                        boxKeyLinks(key, board.get(currentTail));
                        board.get(coordinate).setNode(new NodeLL("|| "));
                        pipesList.addLast(board.get(coordinate).getNode());
                        numPipes+=1;
                    } else if (opt.equals("3")) {
                        currentTail = coordinate;
                        boxKeyLinks(key, board.get(currentTail));
                        board.get(coordinate).setNode(new NodeLL(" o "));
                        pipesList.addLast(board.get(coordinate).getNode());
                        numPipes+=1;
                    } else if (opt.equals("4")) {
                        System.out.println("There is no pipe to delete");
                    }
                }
            }
        }else {
            setBoxFilledNodeType(coordinate, opt);
        }
    }

    public void setBoxFilledNodeType(String coordinate, String opt){
        //set up, right, donw, left links
        int[] key = new int[2];
        String[] keyStr = currentTail.split(",");
        key[0] = Integer.parseInt(keyStr[0]);
        key[1] = Integer.parseInt(keyStr[1]);
        boxKeyLinks(key, board.get(currentTail));
        if (!board.get(coordinate).getNode().getType().equals(" F ") && !board.get(coordinate).getNode().getType().equals(" D ")) {
            if (board.get(randSource).getUp() == board.get(coordinate) || board.get(randSource).getDown() == board.get(coordinate)) {
                if (opt.equals("1") || opt.equals("3")) {
                    System.out.println("You can't put this type of pipe here");
                } else if (opt.equals("2")) {
                    board.get(coordinate).getNode().setType("|| ");
                } else if (opt.equals("4")) {
                    pipesList.delete(board.get(coordinate).getNode());
                    board.get(coordinate).setNode(null);
                    System.out.println("The pipe was deleted");
                }
            }
            //Right Left, Source
            else if (board.get(randSource).getRight() == board.get(coordinate) || board.get(randSource).getLeft() == board.get(coordinate)) {

                if (opt.equals("1")) {
                    board.get(coordinate).getNode().setType(" = ");
                } else if (opt.equals("2") || opt.equals("3")) {
                    System.out.println("You can't put this type of pipe here");
                } else if (opt.equals("4")) {
                    pipesList.delete(board.get(coordinate).getNode());
                    board.get(coordinate).setNode(null);
                    System.out.println("The pipe was deleted");
                }

            }
            //Another position != surce nexts for Left and Right
            else if (board.get(currentTail).getRight() == board.get(coordinate) || board.get(currentTail).getLeft() == board.get(coordinate)) {
                if (pipesList.getTail().getType().equals(" o ")) {
                    boolean condition;
                    if(board.get(currentTail).getDown()==null){
                        condition = board.get(currentTail).getUp().getNode().getType().equals("|| ");
                    }else {
                        condition = board.get(currentTail).getDown().getNode().getType().equals("|| ");
                    }
                    if (condition) {
                        //nothing happens if it is the same(opt3 = " o ")
                        if (opt.equals("1")) {
                            board.get(coordinate).getNode().setType(" = ");
                        } else if (opt.equals("2")) {
                            System.out.println("You can't put this type of pipe here");
                        } else if (opt.equals("4")) {
                            pipesList.delete(board.get(coordinate).getNode());
                            board.get(coordinate).setNode(null);
                            System.out.println("The pipe was deleted");
                        }
                    }
                } else if (pipesList.getTail().getType().equals("|| ")) {
                    System.out.println("You can't put any type of pipe here. There is a vertical pipe previosly");
                } else if (pipesList.getTail().getType().equals(" = ")) {
                    if (opt.equals("1")) {
                        board.get(coordinate).getNode().setType(" = ");
                    } else if (opt.equals("2")) {
                        System.out.println("You can't put this type of pipe here");
                    } else if (opt.equals("3")) {
                        //if get prev is o , cant, else it can
                        if(pipesList.getTail().getPrev().equals(" o ")){
                            System.out.println("You can't put this type of pipe here");
                        }else {
                            board.get(coordinate).getNode().setType(" = ");
                        }
                    } else if (opt.equals("4")) {
                        pipesList.delete(board.get(coordinate).getNode());
                        board.get(coordinate).setNode(null);
                        System.out.println("The pipe was deleted");
                    }
                }
            }
            //Another position != source nexts for Down and Up
            else if (board.get(currentTail).getDown() == board.get(coordinate) || board.get(currentTail).getUp() == board.get(coordinate)) {
                if (pipesList.getTail().getType().equals(" o ")) {
                    boolean condition;
                    if(board.get(currentTail).getRight()==null){
                        condition = board.get(currentTail).getLeft().getNode().getType().equals(" = ");
                    }else {
                        condition = board.get(currentTail).getRight().getNode().getType().equals(" = ");
                    }
                    if (condition) {
                        if (opt.equals("1")) {
                            System.out.println("You can't put this type of pipe here");
                        } else if (opt.equals("2")) {
                            board.get(coordinate).getNode().setType("|| ");
                        } else if (opt.equals("3")) {
                            //if get prev is o , cant, else it can
                            if(pipesList.getTail().getPrev().equals(" o ")){
                                System.out.println("You can't put this type of pipe here");
                            }else {
                                board.get(coordinate).getNode().setType(" o ");
                            }
                        } else if (opt.equals("4")) {
                            pipesList.delete(board.get(coordinate).getNode());
                            board.get(coordinate).setNode(null);
                            System.out.println("The pipe was deleted");
                        }
                    }
                } else if (pipesList.getTail().getType().equals(" = ")) {
                    System.out.println("You can't put any type of pipe here. There is a vertical pipe previosly");
                } else if (pipesList.getTail().getType().equals("|| ")) {
                    if (opt.equals("1")) {
                        System.out.println("You can't put this type of pipe here");
                    } else if (opt.equals("2")) {
                        board.get(coordinate).getNode().setType("|| ");
                    } else if (opt.equals("3")) {
                        //if get prev is o , cant, else it can
                        if(pipesList.getTail().getPrev().equals(" o ")){
                            System.out.println("You can't put this type of pipe here");
                        }else {
                            board.get(coordinate).getNode().setType(" o ");
                        }
                    } else if (opt.equals("4")) {
                        pipesList.delete(board.get(coordinate).getNode());
                        board.get(coordinate).setNode(null);
                        //currentTail = pipesList.getTail().getType();//
                        System.out.println("The pipe was deleted");
                    }
                }
            }
            //TODO
            // posibles casos:
            // (old:=, new:= YES)(old:=, new:|| NO)(old:=, new:o YES)
            // (old:||, new:= NO)(old:||, new:|| YES)(old:||, new:o YES)
            // (old:o, new:= Depends:(old.prev:= YES)(old.prev:|| NO))(old:o, new:|| Depends:(old.prev:= NO)(old.prev:|| YES))(old:o, new:o NO)
        }else {
            System.out.println("You can't edit the Source or the Drain");
        }
    }
    public HashMap<String, Box> getBoard() {
        return board;
    }

    public int getNumPipes() {
        return numPipes;
    }

}
