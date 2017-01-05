package Graphs;

/**
 * Created by Sai Pranav on 1/3/2017.
 */
public class VertexNotFoundException extends Exception {
  String s;
  public VertexNotFoundException(String s) {
    this.s = s;
  }
}
