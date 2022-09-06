package ui;
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
                System.out.println("Please enter a coordinate (eg: 3,4)");
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
