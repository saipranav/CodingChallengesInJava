package Graphs;

import java.util.*;

/**
 * Created by Sai Pranav on 1/4/2017.
 */
public class CycleDetection {
  static Map<String, Vertex> vertices;
  static Map<String, String> parentMap;
  static String startVertex;
  static Set<String> visited;

  public static void main(String args[]){
    Graph undirected = new Graph(false);
    undirected.addEdge("2", "none", 0, "1");
    undirected.addEdge("1", "none", 0, "0");
    undirected.addEdge("0", "none", 0, "2");
    undirected.addEdge("0", "none", 0, "5");
    undirected.addEdge("0", "none", 0, "3");
    undirected.addEdge("3", "none", 0, "4");

    startVertex = "4";
    undirected.breadthFirstTraversal(startVertex);

    vertices = undirected.vertices;
    visited = new HashSet<String>();
    parentMap = new HashMap<String, String>();
    parentMap.put(startVertex, "");
    cycleDetectionInUndirected(startVertex);
    System.out.println();

    Graph directed = new Graph(true);
    directed.addEdge("0", "none", 0, "5");
    directed.addEdge("2", "none", 0, "0");
    directed.addEdge("1", "none", 0, "2");
    directed.addEdge("0", "none", 0, "1");
    directed.addEdge("3", "none", 0, "0");
    directed.addEdge("4", "none", 0, "3");
    directed.getVertex("0").addProperty("color", "white");
    directed.getVertex("1").addProperty("color", "white");
    directed.getVertex("2").addProperty("color", "white");
    directed.getVertex("3").addProperty("color", "white");
    directed.getVertex("4").addProperty("color", "white");
    directed.getVertex("5").addProperty("color", "white");

    startVertex = "4";
    directed.depthFirstTraveral(startVertex);

    vertices = directed.vertices;
    visited = new HashSet<String>();
    parentMap = new HashMap<String, String>();
    parentMap.put(startVertex, "");
    cycleDetectionInDirected(startVertex);
    System.out.println();

    Graph directed2 = new Graph(true);
    directed2.addEdge("A", "none", 0, "B");
    directed2.addEdge("A", "none", 0, "C");
    directed2.addEdge("C", "none", 0, "B");
    directed2.getVertex("A").addProperty("color", "white");
    directed2.getVertex("B").addProperty("color", "white");
    directed2.getVertex("C").addProperty("color", "white");

    startVertex = "A";
    directed2.depthFirstTraveral(startVertex);

    vertices = directed2.vertices;
    visited = new HashSet<String>();
    parentMap = new HashMap<String, String>();
    parentMap.put(startVertex, "");
    cycleDetectionInDirected(startVertex);
    System.out.println();
  }

  private static void cycleDetectionInUndirected(String startVertex){
    Vertex vertex = vertices.get(startVertex);
    Set<String> neighbours = vertex.getNeighbours();
    visited.add(vertex.label);
    for(String neighbour : neighbours){
      if(visited.contains(neighbour) == false){
        //System.out.println(vertex.label + " -> " + neighbour);
        parentMap.put(neighbour, vertex.label);
        cycleDetectionInUndirected(neighbour);
      } else {
        if(parentMap.get(vertex.label).equals(neighbour)){
          //System.out.println("Found back edge " + vertex.label + " -> " + neighbour);
        } else {
          System.out.println("Found cycle " + vertex.label + " -> " + neighbour);
        }
      }
    }
  }

  private static void cycleDetectionInDirected(String startVertex){
    Vertex vertex = vertices.get(startVertex);
    Set<String> neighbours = vertex.getNeighbours();
    vertex.addProperty("color", "grey");
    for(String neighbour : neighbours){
      Vertex neighbourVertex = vertices.get(neighbour);
      if(neighbourVertex.getProperty("color").equals("white")){
        //System.out.println(vertex.label + " -> " + neighbour);
        parentMap.put(neighbour, vertex.label);
        cycleDetectionInDirected(neighbour);
      } else if(neighbourVertex.getProperty("color").equals("grey")){
        System.out.println("Found cycle " + vertex.label + " -> " + neighbour);
      }
    }
    vertex.addProperty("color", "black");
  }
}