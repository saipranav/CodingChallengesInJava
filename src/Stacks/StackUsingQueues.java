package Stacks;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Sai Pranav on 12/25/2016.
 */
class QueueStack<T> {
  Queue<T> q1, q2;

  public QueueStack(){
    q1 = new LinkedList<T>();
    q2 = new LinkedList<T>();
  }

  public void push(T element){
    if(q1.isEmpty()) {
      q1.add(element);
      while(q2.isEmpty() == false){
        q1.add(q2.remove());
      }
    } else {
      q2.add(element);
      while(q1.isEmpty() == false){
        q2.add(q1.remove());
      }
    }
  }

  public T pop(){
    if(q1.isEmpty()){
      return q2.remove();
    }
    return q1.remove();
  }

  public String toString(){
    StringBuilder string = new StringBuilder();
    if(q2.isEmpty()){
      Iterator itr = q1.iterator();
      while(itr.hasNext()){
        string.append(itr.next());
      }
    } else {
      Iterator itr = q2.iterator();
      while(itr.hasNext()){
        string.append(itr.next());
      }
    }
    return string.toString();
  }
}

public class StackUsingQueues{
  public static void main(String args[]){
    QueueStack stack = new QueueStack();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    System.out.println(stack);
    stack.pop();
    stack.pop();
    System.out.println(stack);
  }
}
