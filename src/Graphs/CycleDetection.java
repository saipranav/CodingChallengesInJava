package Graphs;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Sai Pranav on 1/4/2017.
 */
public class CycleDetection {
  public static void main(String args[]){
    Graph undirected = new Graph(false);
    undirected.addEdge("2", "none", 0, "1");
    undirected.addEdge("1", "none", 0, "0");
    undirected.addEdge("0", "none", 0, "2");
    undirected.addEdge("0", "none", 0, "5");
    undirected.addEdge("0", "none", 0, "3");
    undirected.addEdge("3", "none", 0, "4");

    String startVertex = "4";
    undirected.breadthFirstTraversal(startVertex);

    Set<String> visited = new HashSet<String>();
    Stack<String> stack = new Stack<String>();
    Map<String, Vertex> vertices = undirected.vertices;

    if(vertices.containsKey(startVertex) == false){
      return;
    }

    stack.push(vertices.get(startVertex).label);

    while(stack.isEmpty() == false) {
      Vertex vertex = vertices.get(stack.pop());
      Set<String> neighbours = vertex.getNeighbours();
      for (String neighbour : neighbours) {
        if ( !(vertex.label.equals(neighbour)) && visited.contains(neighbour) == true) {
          System.out.println("Contains a cycle " + vertex.label + " -> " + neighbour);
        }
        if (visited.contains(neighbour) == false) {
          stack.push(neighbour);
        }
      }
      visited.add(vertex.label);
    }
  }
}
