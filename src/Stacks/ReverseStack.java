package Stacks;

import java.util.Stack;

/**
 * Created by Sai Pranav on 12/24/2016.
 */
public class ReverseStack {
  public static void main(String args[]){
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    System.out.println(stack);
    stack = reverseStack(stack);
    System.out.println(stack);
  }

  public static Stack<Integer> reverseStack(Stack<Integer> stack){
    if(stack.isEmpty() == true){
      return stack;
    }
    Integer currentTop = stack.pop();
    stack = reverseStack(stack);
    stack = insertToBottom(stack, currentTop);
    return stack;
  }

  public static Stack<Integer> insertToBottom(Stack<Integer> stack, Integer item){
    if(stack.isEmpty() == true){
      stack.push(item);
    } else {
      Integer currentTop = stack.pop();
      insertToBottom(stack, item);
      stack.push(currentTop);
    }
    return stack;
  }
}
