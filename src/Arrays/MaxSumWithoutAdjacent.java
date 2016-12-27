package Arrays;

/**
 * Created by Sai Pranav on 12/22/2016.
 */
public class MaxSumWithoutAdjacent {
  public static void main(String args[]){
    int[] array = new int[]{5, 5, 10, 40, 50, 35};
    System.out.println(maxSumWithoutAdjacent(array));
  }

  static int maxSumWithoutAdjacent(int[] array){
    int globalMax = Integer.MIN_VALUE;
    int localMaxOdd = Integer.MIN_VALUE;
    int localMaxEven = Integer.MIN_VALUE;
    for (int index = 0; index < array.length; index++) {
      if (index == 0) {
        localMaxEven = array[index];
      } else if (index == 1){
        localMaxOdd = array[index];
      } else if (index % 2 != 0){
        // For odd indexed numbers
        localMaxOdd = max(localMaxOdd, array[index], localMaxOdd + array[index]);
      } else {
        // For even indexed numbers
        localMaxEven = max(localMaxEven, array[index], localMaxEven + array[index]);
      }
      globalMax = max(localMaxOdd, localMaxEven, globalMax);
    }
    return globalMax;
  }

  static int max(int a, int b, int c){
    int result = Integer.MIN_VALUE;
    result = Math.max(a,b);
    result = Math.max(result, c);
    return result;
  }
}
