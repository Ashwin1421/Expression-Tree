package aj.custom.tree;

import java.util.List;
import java.util.Stack;

public class SyntaxTree implements Tree {
    private Node root;

    @Override
    public void add(Node parent, Node child) {
        new BinTree(getRoot()).add(parent, child);
    }

    @Override
    public void add(Node child) {
        new BinTree(getRoot()).add(child);
    }

    @Override
    public void remove(Node node) {
        new BinTree(getRoot()).remove(node)  ;
    }

    @Override
    public int height(Node root) {
        return new BinTree(root).height(root);
    }

    @Override
    public void levelOrder(Node root, List levelList, int level) {
        new BinTree(root).levelOrder(root, levelList, level);
    }

    @Override
    public void levelOrder(Node root, String[] levelStringArr, int level, int start, int end) {
        new BinTree(root).levelOrder(root, levelStringArr, level, start, end);
    }

    private boolean isOperator(char c){
        switch (c){
            case '+':
            case '-':
            case '*':
            case '/':
            case '^': return true;
            default: return false;
        }
    }

    private int priority(char op){
        switch(op){
            case '+':
            case '-': return 1;
            case '*':
            case '/':
            case '^': return 2;
            default: return 0;
        }
    }

    public Node parse(String expression){
        Stack<Character> input = new Stack<>();
        Stack<Node> op = new Stack<>();
        Stack<Node> nodes = new Stack<>();
        for(int i=0;i<expression.length();i++){
            input.push(expression.charAt(i));
        }

        while(!input.empty()){
            Character temp = input.pop();
            if(Character.isDigit(temp)){
                nodes.push(new Node(temp));
            }
            if(temp.equals(')')){
                op.push(new Node(temp));
            }
            if(isOperator(temp)){
                boolean pushed = false;
                while(!pushed){
                    if(op.empty()){
                        op.push(new Node(temp));
                        pushed = true;
                    }else if(op.peek().getData().equals(')')){
                        op.push(new Node(temp));
                        pushed = true;
                    }else if(
                            priority(temp) > priority((char)op.peek().getData()) ||
                            priority(temp) == priority((char)op.peek().getData())
                    ){
                        op.push(new Node(temp));
                        pushed= true;
                    }else{
                        build(nodes, op);
                    }
                }
            }
            if(temp.equals('(')){
                while(!op.peek().getData().equals(')')){
                    build(nodes, op);
                }
                op.pop();
            }
        }
        while(!op.empty()){
            build(nodes, op);
        }
        return nodes.peek();
    }

    private void build(Stack<Node> nodes, Stack<Node> ops){
        Node op = ops.pop();
        op.setLeft(nodes.pop());
        op.setRight(nodes.pop());
        nodes.push(op);
    }
    public double evaluate(Node root){
        if(root == null){
            return 0;
        }
        if(root.getLeft() == null && root.getRight() == null){
            return Integer.parseInt(Character.toString((char)root.getData()));
        }

        double leftSubTree = evaluate(root.getLeft());
        double rightSubTree = evaluate(root.getRight());

        switch (Character.valueOf((char)root.getData())){
            case '+': return leftSubTree+rightSubTree;
            case '-': return leftSubTree-rightSubTree;
            case '*': return leftSubTree*rightSubTree;
            case '/': return leftSubTree/rightSubTree;
            case '^': return Math.pow(leftSubTree, rightSubTree);
        }

        return 0;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
