package Graphs;

import java.util.*;

/**
 * Created by Sai Pranav on 1/5/2017.
 */
public class FindPath {
  private static Map<String, String> parent;
  private static Map<String, Vertex> vertices;
  private static Set<String> visited;

  public static void main(String args[]){
    Graph undirected = new Graph(false);
    undirected.addEdge("0", "none", 0, "6");
    undirected.addEdge("0", "none", 0, "2");
    undirected.addEdge("6", "none", 0, "1");
    undirected.addEdge("1", "none", 0, "3");
    undirected.addEdge("3", "none", 0, "8");
    undirected.addEdge("8", "none", 0, "2");
    undirected.addEdge("3", "none", 0, "5");
    undirected.addEdge("1", "none", 0, "4");
    undirected.addEdge("4", "none", 0, "10");
    undirected.addVertex("9");

    vertices = undirected.vertices;
    parent = new HashMap<String, String>();
    visited = new HashSet<String>();

    parent.put("0", "");
    new FindPath().findPath("0", "0", "10");

    System.out.println();

    Graph directed = new Graph(true);
    directed.addEdge("0", "none", 0, "6");
    directed.addEdge("0", "none", 0, "2");
    directed.addEdge("6", "none", 0, "1");
    directed.addEdge("1", "none", 0, "3");
    directed.addEdge("3", "none", 0, "8");
    directed.addEdge("8", "none", 0, "3");
    directed.addEdge("8", "none", 0, "2");
    directed.addEdge("3", "none", 0, "5");
    directed.addEdge("1", "none", 0, "4");
    directed.addEdge("5", "none", 0, "1");
    directed.addEdge("5", "none", 0, "10");
    directed.addEdge("4", "none", 0, "10");
    directed.addVertex("9");

    vertices = directed.vertices;
    parent = new HashMap<String, String>();
    visited = new HashSet<String>();

    parent.put("0", "");
    new FindPath().findPath("0", "0", "10");
  }

  private boolean findPath(String current, String source, String destination){
    Vertex currentVertex = vertices.get(current);
    visited.add(current);
    if(current.equals(destination)){
      String end = destination;
      String start = parent.get(current);
      System.out.print(end);
      while(start.equals(source) == false){
        System.out.print(" <- " + start);
        end = start;
        start = parent.get(end);
      }
      System.out.println(" <- " + start);
      return true;
    }
    Set<String> neighbours = currentVertex.getNeighbours();
    for(String neighbour : neighbours){
      if(visited.contains(neighbour) == false){
        parent.put(neighbour, current);
        if( findPath(neighbour, source, destination) == true ){
          return true;
        }
      }
    }
    return false;
  }
}
