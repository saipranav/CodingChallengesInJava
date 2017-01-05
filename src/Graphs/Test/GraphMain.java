package Graphs.Test;

import Graphs.Graph;

/**
 * Created by Sai Pranav on 1/3/2017.
 */
public class GraphMain {
  public static void main(String args[]){
    Graph directedGraph = new Graph(true);
    directedGraph.addEdge("A", "none", 0, "B");
    String firstVertex = directedGraph.getFirstVertex();
    directedGraph.breadthFirstTraversal(firstVertex);

    Graph unDirectedGraph = new Graph(false);
    unDirectedGraph.addEdge("A", "none", 0, "B");
    firstVertex = directedGraph.getFirstVertex();
    unDirectedGraph.breadthFirstTraversal(firstVertex);
  }
}
