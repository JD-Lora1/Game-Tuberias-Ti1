package model;

public class Scoreboard {

    private Node root;

    public void addNode(Node node){
        if(this.root ==null){
            this.root =node;
        }else{
            addNode(node,root);
        }
    }

    private void addNode(Node node,Node current){
        if(node.getScore() < current.getScore()){
            if(current.getLeft() != null){
                addNode(node,current.getLeft());
            }else{
                current.setLeft(node);
            }
        }
        if(node.getScore() > current.getScore()){
            if(current.getRight() != null){
                addNode(node,current.getRight());
            } else{
                current.setRight(node);
            }
        }

    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node current){
        if(current==null){
            return;
        }
        inOrder(current.getRight());
        System.out.println(current.toString());
        inOrder(current.getLeft());
    }

}