package Queues;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Sai Pranav on 12/25/2016.
 */
public class MaxInKSizedSubArrays {
  public static void main(String args[]){
    int[] array = new int[]{8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
    int[] result = maxInKSizedSubArrays(array, 4);
    for (int index = 0; index < result.length; index++){
      System.out.printf("%d ",result[index]);
    }
  }

  public static int[] maxInKSizedSubArrays(int[] array, int size){
    int[] result = new int[( array.length - size ) + 1];
    int rIndex = 0;
    int max = Integer.MIN_VALUE;
    Queue<Integer> q = new LinkedList<Integer>();
    for (int index = 0; index < size; index++){
      q.add(array[index]);
      max = Math.max(max, array[index]);
    }
    result[rIndex] = max;
    for (int index = size; index < array.length; index++){
      if(max != q.element()){
        q.remove();
        q.add(array[index]);
        max = Math.max(max, array[index]);
      } else {
        q.remove();
        q.add(array[index]);
        max = Integer.MIN_VALUE;
        Iterator itr = q.iterator();
        while(itr.hasNext()){
          max = Math.max(max, (int)itr.next());
        }
      }
      rIndex++;
      result[rIndex] = max;
    }
    return result;
  }
}
