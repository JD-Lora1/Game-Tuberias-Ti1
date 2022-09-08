package model;

public class Node {

    public Node(String nickName,int score){
        this.nickName=nickName;
        this.score=score;

    }

    private String nickName;

    private Node right;

    private Node left;

    private int score;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Name: " + nickName + " Score: " + score;
    }
}