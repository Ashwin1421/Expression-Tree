package com.cloudninetech.graph;

import java.util.HashMap;
import java.util.Map;

public class Node<T> {
    private T data;
    private Map<String, String> metadata;

    public Node(T data){
        this.data = data;
        this.metadata = new HashMap<>();
    }
    public T getData() {
        return data;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
