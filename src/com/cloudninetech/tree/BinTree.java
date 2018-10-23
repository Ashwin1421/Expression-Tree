package com.cloudninetech.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinTree implements Tree {
    private Node root;


    public BinTree(Node root){
        this.root = root;
    }
    @Override
    public void add(Node parent, Node child) {
        if(parent.getLeft() == null){
            parent.setLeft(child);
        }else if(parent.getRight() == null){
            parent.setRight(child);
        }
    }

    @Override
    public void add(Node child) {
        Node currentNode = root;
        Queue<Node> queue = new LinkedList();
        queue.add(currentNode);
        while(!queue.isEmpty()){
            currentNode = queue.poll();
            if(currentNode.getLeft() == null){
                currentNode.setLeft(child);
                break;
            }else{
                queue.add(currentNode.getLeft());
            }

            if(currentNode.getRight() == null){
                currentNode.setRight(child);
                break;
            }else{
                queue.add(currentNode.getRight());
            }
        }
    }

    @Override
    public void remove(Node node) {
        if(node.getLeft() == null && node.getRight() == null){

        }
    }

    @Override
    public int height(Node root) {
        if(root == null){
            return 0;
        }
        return Math.max(height(root.getLeft()), height(root.getRight()))+1;
    }

    @Override
    public void levelOrder(Node root, List levelList, int level) {
        if(root == null) return;

        if(level == 1){
            levelList.add(root.getData());
        }else{
            levelOrder(root.getLeft(), levelList, level-1);
            levelOrder(root.getRight(), levelList, level-1);
        }
    }

    @Override
    public void levelOrder(Node root, String[] levelStringArr, int level, int start, int end) {
        if(root == null){
            return;
        }
        if(level == 1){
            levelStringArr[(start+end)/2] = root.getData().toString();
        }else{
            int mid = (start+end)/2;
            levelOrder(root.getLeft(), levelStringArr, level-1, start, mid-1 );
            levelOrder(root.getRight(), levelStringArr, level-1, mid+1, end);
        }
    }


}
