package Stacks;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Sai Pranav on 12/25/2016.
 */

class StackQueue<T>{
  Stack<T> s, dummy;

  public StackQueue(){
    s = new Stack<T>();
    dummy = new Stack<T>();
  }

  public void enqueue(T element){
    while(s.isEmpty() == false){
      dummy.push(s.pop());
    }
    s.push(element);
    while(dummy.isEmpty() == false){
      s.push(dummy.pop());
    }
  }

  public T dequeue(){
    return s.pop();
  }

  public String toString(){
    StringBuilder string = new StringBuilder();
    Iterator itr = s.iterator();
    while(itr.hasNext()){
      string.append(itr.next());
    }
    return string.toString();
  }
}

public class QueueUsingStacks {
  public static void main(String args[]){
    StackQueue queue = new StackQueue();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    System.out.println(queue);
    queue.dequeue();
    queue.dequeue();
    System.out.println(queue);
  }
}
