package Graphs;

import java.util.*;

/**
 * Created by Sai Pranav on 1/7/2017.
 */
public class TopologicalSort {
  static Set<String> visited;
  static Queue<String> queue;
  static Map<String, Vertex> vertices;
  static Stack<String> stack;

  public static void main(String args[]) {
    Graph directed = new Graph(true);
    directed.addEdge("5", "none", 0, "0");
    directed.addEdge("5", "none", 0, "2");
    directed.addEdge("4", "none", 0, "0");
    directed.addEdge("4", "none", 0, "1");
    directed.addEdge("2", "none", 0, "3");
    directed.addEdge("3", "none", 0, "1");
    vertices = directed.vertices;

    stack = new Stack<String>();
    visited = new HashSet<String>();

    for(String vertex : vertices.keySet()) {
      if(visited.contains(vertex) == false) {
        printTopologicalSortByDfs(vertex);
      }
    }
    while(stack.isEmpty() == false){
      System.out.print(stack.pop() + " ");
    }
    System.out.println();


    queue = new LinkedList<String>();
    printTopologicalSortByDegree();
    System.out.println();



  }

  private static void printTopologicalSortByDfs(String startVertex) {
    Vertex current = vertices.get(startVertex);
    Set<String> neighbours = current.getNeighbours();
    visited.add(startVertex);

    for(String neighbour : neighbours){
      if(visited.contains(neighbour) == false){
        printTopologicalSortByDfs(neighbour);
      }
    }

    stack.push(startVertex);
  }

  private static void printTopologicalSortByDegree(){

    for(String vertex : vertices.keySet()){
      Vertex current = vertices.get(vertex);
      current.addProperty("inDegree", 0);
    }

    for(String vertex : vertices.keySet()){
      Vertex current = vertices.get(vertex);
      Set<String> neighbours = current.getNeighbours();

      for(String neighbour : neighbours){
        Vertex neighbourVertex = vertices.get(neighbour);
        int count = (int) neighbourVertex.getProperty("inDegree");
        neighbourVertex.addProperty("inDegree", count+1);
      }
    }

    for(String vertex : vertices.keySet()){
      Vertex current = vertices.get(vertex);
      if( (int)current.getProperty("inDegree") == 0 ){
        queue.add(vertex);
      }
    }

    while(queue.isEmpty() == false){
      String vertex = queue.remove();
      System.out.print(vertex + " ");
      Vertex current = vertices.get(vertex);
      Set<String> neighbours = current.getNeighbours();

      for(String neighbour : neighbours){
        Vertex neighbourVertex = vertices.get(neighbour);
        int count = (int) neighbourVertex.getProperty("inDegree");
        neighbourVertex.addProperty("inDegree", count-1);
        if( (int)neighbourVertex.getProperty("inDegree") == 0 ){
          queue.add(neighbour);
        }
      }
    }
  }
}
