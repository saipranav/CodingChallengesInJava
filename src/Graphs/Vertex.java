package Graphs;

import java.util.*;

/**
 * Created by Sai Pranav on 1/3/2017.
 */
public class Vertex {
  public String label;
  public List<Edge> edges;
  public Map<String, Object> property;

  public Vertex(String label){
    this.label = label;
    this.edges = new ArrayList<Edge>();
    this.property = new HashMap<String, Object>();
  }

  public boolean addEdge(Edge edge){
    if(edges.contains(edge) == false){
      edges.add(edge);
      return true;
    }
    return false;
  }

  // returns edges having same end but different labels
  public List<Edge> getEdges(String end){
    List<Edge> returnable = new ArrayList<Edge>();
    for(Edge edge : edges){
      if(edge.end.equals(end)){
        returnable.add(edge);
      }
    }
    return returnable;
  }

  // returns an edge as there can be only one with same end and same label
  public Edge getEdge(String edgeLabel, String end){
    for(Edge edge : edges){
      if(edge.end.equals(end) && edge.label.equals(edgeLabel)){
        return edge;
      }
    }
    return null;
  }

  public boolean removeEdges(String end){
    for(Edge edge : edges){
      if(edge.end.equals(end)){
        return edges.remove(edge);
      }
    }
    return false;
  }

  public boolean removeEdge(String edgeLabel, String end){
    for(Edge edge : edges){
      if(edge.end.equals(end) && edge.label.equals(edgeLabel)){
        return edges.remove(edge);
      }
    }
    return false;
  }

  public void addProperty(String key, Object value){
    this.property.put(key, value);
  }

  public Object getProperty(String key){
    return this.property.get(key);
  }

  public void resetProperties(){
    this.property.clear();
  }

  public Set<String> getNeighbours(){
    Set<String> returnable = new HashSet<String>();
    for(Edge edge : edges){
      returnable.add(edge.end);
    }
    return returnable;
  }

  public int numberOfEdges(){
    return edges.size();
  }

  public boolean equals(Object b){
    Vertex anotherVertex = (Vertex) b;
    if(this.label.equals(anotherVertex.label)){
      return true;
    }
    return false;
  }

  public int hashCode(){
    return label.hashCode();
  }
}
