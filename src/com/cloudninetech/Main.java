package com.cloudninetech;

import com.cloudninetech.tree.Node;
import com.cloudninetech.tree.SyntaxTree;
import com.cloudninetech.tree.Tree;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Tree expTree = new SyntaxTree();
        String exp = "((3+4)-8)*(5^2)";
        Node root = ((SyntaxTree) expTree).parse(exp);
        int arrLength = (int)(Math.pow(2, expTree.height(root))) - 1;
        for(int i=1;i<=expTree.height(root);i++){
            String[] level = new String[arrLength];
            Arrays.fill(level," ");
            expTree.levelOrder(root, level, i, 0, arrLength);
            System.out.println(Arrays.toString(level));
        }
        System.out.println(((SyntaxTree) expTree).evaluate(root));
    }
}
