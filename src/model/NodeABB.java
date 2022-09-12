package model;
public class NodeABB {

    public NodeABB(String nickName) {
        this.nickName = nickName;

    }

    private String nickName;

    private NodeABB right;

    private NodeABB left;

    private int score;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public NodeABB getRight() {
        return right;
    }

    public void setRight(NodeABB right) {
        this.right = right;
    }

    public NodeABB getLeft() {
        return left;
    }

    public void setLeft(NodeABB left) {
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
