package Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sai Pranav on 11/19/2016.
 */
public class PairsGivenSum {
 public static void main(String args[]){
    int a[] = {1, 4, 45, 6, 10, 8 , -8};
    int sum = 10;
    PairsGivenSum.pairsOfGivenSum(a, sum);
 }

 public static void pairsOfGivenSum(int array[], int sum){
   int min = Integer.MAX_VALUE;
   Map<Integer, Integer> data = new HashMap<Integer, Integer>();
   for (int index = 0; index < array.length; index++) {
    if (min > array[index]) {
      min = array[index];
    }
   }
   if (min < 0) {
     min = min * -1;
     for (int index = 0; index < array.length; index++) {
       array[index] = array[index] + min;
       data.put(array[index], index);
     }
     sum = sum + ( 2 * min );
   } else {
     for (int index = 0; index < array.length; index++) {
       data.put(array[index], index);
     }
   }
   for (int index = 0; index < array.length; index++) {
     if (data.containsKey(sum - array[index])) {
       if (index != data.get(sum - array[index])){
         System.out.println(index + "," + data.get(sum - array[index]));
       }
     }
   }


 }
}
