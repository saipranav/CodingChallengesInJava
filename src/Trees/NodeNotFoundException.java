package Trees;

/**
 * Created by Sai Pranav on 12/27/2016.
 */
public class NodeNotFoundException extends Exception{
  String reason;

  public NodeNotFoundException(){
    super();
    reason = "Not found";
  }

  public NodeNotFoundException(String reason){
    super();
    this.reason = reason;
  }

  public String toString(){
    return this.reason;
  }
}
