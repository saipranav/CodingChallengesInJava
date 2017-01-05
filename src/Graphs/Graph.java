package Graphs;

import java.util.*;

/**
 * Created by Sai Pranav on 1/3/2017.
 */
public class Graph {
  public Map<String, Vertex> vertices;
  public boolean isDirected;

  public Graph(boolean isDirected) {
    vertices = new HashMap<String, Vertex>();
    this.isDirected = isDirected;
  }

  public void addVertex(String vertexLabel){
    if(vertices.containsKey(vertexLabel) == false){
      Vertex newVertex = new Vertex(vertexLabel);
      vertices.put(vertexLabel, newVertex);
    }
  }

  public Vertex getVertex(String vertexLabel){
    return vertices.get(vertexLabel);
  }

  public String getFirstVertex(){
    return (String) vertices.keySet().toArray()[0];
  }

  public boolean removeVertex(String vertexLabel){
    if(vertices.containsKey(vertexLabel) == false){
      return false;
    }

    vertices.remove(vertexLabel);

    if(isDirected == false){
      Set<String> allVertices = vertices.keySet();
      for(String vertex : allVertices){
        vertices.get(vertex).removeEdges(vertexLabel);
      }
    }
    return true;
  }

  // O(E) searches in edge lists for duplicate edges
  public void addEdge(String start, String edgeLabel, double value, String end) {
    Vertex newStart;
    Edge newEdge = new Edge(start, edgeLabel, value, end);

    if (vertices.containsKey(start)) {
      newStart = vertices.get(start);
    } else {
      newStart = new Vertex(start);
      vertices.put(start, newStart);
      if (vertices.containsKey(end) == false) {
        vertices.put(end, new Vertex(end));
      }
    }
    newStart.addEdge(newEdge);

    if (isDirected == false) {
      Vertex newStartReverse;
      Edge newEdgeReverse = new Edge(end, edgeLabel, value, start);

      if (vertices.containsKey(end)) {
        newStartReverse = vertices.get(end);
      } else {
        newStartReverse = new Vertex(end);
        vertices.put(end, newStartReverse);
      }
      newStartReverse.addEdge(newEdgeReverse);
    }
  }

  // returns null if start or end vertex not found
  public Edge getEdge(String start, String edgeLabel, String end) throws VertexNotFoundException {
    if (vertices.containsKey(start)) {
      return vertices.get(start).getEdge(edgeLabel, end);
    }
    return null;
  }

  public boolean removeEdge(String start, String edgeLabel, String end, double value) {
    if(vertices.containsKey(start)){
      if(isDirected == false){
        vertices.get(end).removeEdge(edgeLabel, start);
      }
      return vertices.get(start).removeEdge(edgeLabel, end);
    }
    return false;
  }

  public void breadthFirstTraversal(String startVertex){
    Set<String> visited = new HashSet<String>();
    Queue<String> queue = new LinkedList<String>();

    if(vertices.containsKey(startVertex) == false){
      return;
    }

    queue.add(vertices.get(startVertex).label);

    while(queue.isEmpty() == false){
      Vertex vertex = vertices.get( queue.remove() );
      Set<String> neighbours = vertex.getNeighbours();
      for (String neighbour : neighbours){
        System.out.print(vertex.label + " -> " + neighbour + " | ");
        if(visited.contains(neighbour) == false){
          queue.add(neighbour);
        }
      }
      if(neighbours.size() > 0){
        System.out.println();
      }
      visited.add(vertex.label);
    }
  }

  public void depthFirstTraveral(String startVertex){
    Set<String> visited = new HashSet<String>();
    Stack<String> stack = new Stack<String>();

    if(vertices.containsKey(startVertex) == false){
      return;
    }

    stack.push(vertices.get(startVertex).label);

    while(stack.isEmpty() == false){
      Vertex vertex = vertices.get( stack.pop() );
      if(visited.contains(vertex.label) == false){
        Set<String> neighbours = vertex.getNeighbours();
        for (String neighbour : neighbours){
          System.out.print(vertex.label + " -> " + neighbour + " | ");
          stack.push(neighbour);
        }
        if(neighbours.size() > 0){
          System.out.println();
        }
        visited.add(vertex.label);
      }
    }
  }
}
