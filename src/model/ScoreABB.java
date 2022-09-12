package model;

public class ScoreABB {

    private NodeABB root;

    public void addNode(NodeABB node){
        if(this.root ==null){
            this.root =node;
        }else{
            addNode(node,root);
        }
    }

    private void addNode(NodeABB node,NodeABB current){
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

    private void inOrder(NodeABB current){
        if(current==null){
            return;
        }
        inOrder(current.getRight());
        System.out.println(current.getScore());
        inOrder(current.getLeft());
    }

}
