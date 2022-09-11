package model;

public class Control {

    private Game game;
    private Scoreboard scoreboard;

    public Control() {
        scoreboard = new Scoreboard();
    }

    public void startGame(String nickname){
        game = new Game(new Player(nickname));
        game.createBoxes();
    }

    public Game getGame() {
        return game;
    }
    public void toPrintGame(){
        game.print();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void addNewScore(){
        scoreboard.addNode(new NodeABB(game.getPlayerName(), game.getScore()));
    }
}
