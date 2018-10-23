package aj.custom.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiGraph implements Graph {
    private Map<Node, List<Node>> graph = new HashMap<>();

    @Override
    public void addEdge(Node n1, Node n2) {
        //n1 -> n2
        if(!graph.containsKey(n1)){
            add(n1);
        }
        graph.get(n1).add(n2);
    }

    @Override
    public void add(Node n1) {
        graph.put(n1, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "DiGraph{" +"\n"+
                "graph=" + graph +"\n"+
                '}';
    }

    public void dfs(Node n){
        for(Node nb : graph.get(n)){
            System.out.println(nb);
            if(graph.get(nb) != null) {
                for (Node nb1 : graph.get(nb)) {
                    System.out.println(nb1);
                }
            }
        }
    }
}
