package model;

public class NodeABB {
    private String nickName;

    private NodeABB right;

    private NodeABB left;

    private double score;

    public NodeABB(String nickName, double score){
        this.nickName = nickName;
        this.score = score;
    }

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Name: " + nickName + " Score: " + score;
    }
}