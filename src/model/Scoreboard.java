package model;

public class Scoreboard {

    private NodeABB root;

    public void addNode(NodeABB nodeABB){
        if(this.root ==null){
            this.root = nodeABB;
        }else{
            addNode(nodeABB,root);
        }
    }

    private void addNode(NodeABB nodeABB, NodeABB current){
        if(nodeABB.getScore() < current.getScore()){
            if(current.getLeft() != null){
                addNode(nodeABB,current.getLeft());
            }else{
                current.setLeft(nodeABB);
            }
        }
        if(nodeABB.getScore() > current.getScore()){
            if(current.getRight() != null){
                addNode(nodeABB,current.getRight());
            } else{
                current.setRight(nodeABB);
            }
        }

    }

    public void inOrder(){
        System.out.println("Scores: ");
        if (root==null)
            System.out.println("- There are no scores yet");
        else
            inOrder(root);
        System.out.println("");
    }

    private void inOrder(NodeABB current){
        if(current==null){
            return;
        }
        inOrder(current.getRight());
        System.out.println(current.toString());
        inOrder(current.getLeft());
    }

}