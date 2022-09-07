package ui;
import java.text.ParseException;
import java.util.Scanner;
import model.Control;

public class Main {

    private Control pipeMania;
    private Scanner sc;

    public Main() {
        pipeMania = new Control();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main main = new Main();
        String select;
        do{
            select = main.options();
            main.menu(select);

        }while (!select.equals("3"));
    }

    public String options (){
        System.out.println("\n1.New game\n2.Score \n3.Exit\n");
        String select = sc.nextLine();
        return select;
    }

    public void menu(String select){
        switch (select){

            case "1":
                System.out.println("Type your nickname");
                String nickname = sc.nextLine();
                pipeMania.startGame(nickname);

                String select2;
                do{
                    select2 = options2();
                    menu2(select2);
                    pipeMania.toPrintGamge();

                }while (!select2.equals("3"));

                break;

            case "2":
                //ABB
                break;

            case "3":
                System.out.println("Bye!");
                break;
        }
    }

    public String options2 (){
        System.out.println("\n1.Lay pipe\n2.Simulate water flow \n3.Exit\n");
        String select2 = sc.nextLine();
        return select2;
    }

    public void menu2(String select2){
        switch (select2){

            case "1":
                boolean cond = true;
                String coordinate="";
                while (cond){
                    try {
                        System.out.println("Please enter the row (eg:5)");
                        int xAxis = Integer.parseInt(sc.nextLine());
                        coordinate = xAxis+"";
                        System.out.println("Please enter the column (eg:0)");
                        int yAxis = Integer.parseInt(sc.nextLine());
                        coordinate = coordinate+","+yAxis;
                        //Test if it exists
                        pipeMania.getGame().getBoard().get(coordinate).getType();
                        cond = false;
                    }catch (NumberFormatException e){
                        System.out.println("Please provide a number");
                    }catch (NullPointerException e){
                        System.out.println("Please provide a valid coordinate");
                    }
                }
                System.out.println("Please enter one of these optio1ns:\n" +
                        " * 1: Horizontal pipe (=)\n" +
                        " * 2: Vertical pipe (||)\n" +
                        " * 3: Circular pipe (o)\n" +
                        " * 4: Delete pipe");

                String type = sc.nextLine();
                pipeMania.getGame().setBoxType(coordinate, type);
                break;

            case "2":
                //ABB
                break;

            case "3":
                System.out.println("Bye!");
                break;
        }
    }

}
