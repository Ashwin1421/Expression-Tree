package com.cloudninetech.tree;

import java.util.List;

public interface Tree {

    void add(Node parent, Node child);
    void add(Node child);
    void remove(Node node);
    abstract int height(Node root);
    void levelOrder(Node root, List levelList, int level);
    void levelOrder(Node root, String[] levelStringArr, int level, int start, int end);
}
