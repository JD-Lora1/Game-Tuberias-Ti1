package model;
import model.Game;

public class Control {

    private Game game;

    public void startGame(String nickname){
        game = new Game(new Player(nickname));
        game.createBoxes();
        game.print();
    }

    public Game getGame() {
        return game;
    }
    public void toPrintGamge(){
        game.print();
    }
}
