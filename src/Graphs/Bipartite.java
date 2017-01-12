package Graphs;

import java.util.*;

/**
 * Created by Sai Pranav on 1/12/2017.
 */
public class Bipartite {
  static Map<String, Vertex> vertices;
  static Set<String> visited;
  static Queue<String> queue;

  public static void main(String args[]){
    Graph undirected = new Graph(false);

    undirected.addEdge("A", "none", 0, "B");
    undirected.addEdge("C", "none", 0, "D");
    undirected.addEdge("A", "none", 0, "D");
    undirected.addEdge("C", "none", 0, "B");

    undirected.addEdge("E", "none", 0, "F");
    undirected.addEdge("F", "none", 0, "G");
    undirected.addEdge("G", "none", 0, "E");

    vertices = undirected.vertices;
    visited = new HashSet<String>();
    queue = new LinkedList<String>();

    boolean flag = true;
    for(String vertex : vertices.keySet()){
      if(visited.contains(vertex) == false){
        Vertex currentVertex = vertices.get(vertex);
        currentVertex.addProperty("color", "black");
        if (findBipartite(vertex) == false){
          flag = false;
        }
      }
    }
    if(flag == true){
      System.out.println("Bipartite");
    } else {
      System.out.println("Not a Bipartite");
    }
  }

  private static boolean findBipartite(String startVertex){
    queue.add(startVertex);
    while(queue.isEmpty() == false){
      String current  = queue.remove();
      Vertex currentVertex = vertices.get(current);
      Set<String> neighbours = currentVertex.getNeighbours();
      for(String neighbour : neighbours){
        Vertex neighbourVertex = vertices.get(neighbour);
        if(visited.contains(neighbour) == false){
          if(currentVertex.getProperty("color").equals("red")){
            neighbourVertex.addProperty("color", "black");
          } else {
            neighbourVertex.addProperty("color", "red");
          }
          queue.add(neighbour);
        } else {
          if(currentVertex.getProperty("color").equals(neighbourVertex.getProperty("color"))){
            return false;
          }
        }
      }
      visited.add(current);
    }
    return true;
  }
}
