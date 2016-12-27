package Stacks;

import java.util.Stack;

/**
 * Created by Sai Pranav on 12/25/2016.
 */
public class SortStack {
  public static void main(String args[]){
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(5);
    stack.push(4);
    stack.push(3);
    stack.push(2);
    stack.push(1);
    System.out.println(stack);
    stack = sortStack(stack);
    System.out.println(stack);
  }

  public static Stack sortStack(Stack<Integer> stack){
    if(stack.isEmpty() == true){
      return stack;
    }
    Integer currentTop = stack.pop();
    stack = sortStack(stack);
    stack = sortInsert(stack, currentTop);
    return stack;
  }

  public static Stack sortInsert(Stack<Integer> stack, Integer element){
    if (stack.isEmpty() == true || element > stack.peek()){
      stack.push(element);
    } else {
      Integer currentTop = stack.pop();
      stack = sortInsert(stack, element);
      stack.push(currentTop);
    }
    return stack;
  }
}
