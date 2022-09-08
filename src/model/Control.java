package model;

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
    public void toPrintGame(){
        game.print();
    }
}
