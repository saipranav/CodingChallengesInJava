package Graphs;

/**
 * Created by Sai Pranav on 1/3/2017.
 */
public class Edge {
  public String label;
  public String start;
  public double value;
  public String end;


  public Edge(String label, String start, double value, String end){
    this.label = label;
    this.start = start;
    this.value = value;
    this.end = end;
  }

  public boolean equals(Object b){
    Edge anotherEdge = (Edge) b;
    if(this.label.equals(anotherEdge.label)
        && this.start.equals(anotherEdge.start)
        && this.end.equals(anotherEdge.end)
        && this.value == anotherEdge.value){
      return true;
    }
    return false;
  }

  public int hashCode(){
    return (int)value + label.charAt(0);
  }
}
