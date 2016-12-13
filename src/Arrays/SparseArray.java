package Arrays;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by Sai Pranav on 11/19/2016.
 */
public class SparseArray {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int n, q;
    String temp;
    Map<String, Integer> data = new HashMap<String, Integer>();

    n = scanner.nextInt();
    scanner.nextLine();
    for (int index = 0; index < n; index++){
      temp = scanner.nextLine();
      if ( data.containsKey(temp) == false) {
        data.put(temp, 1);
      } else {
        data.put(temp, data.get(temp) + 1);
      }
    }

    q = scanner.nextInt();
    scanner.nextLine();
    for (int index = 0; index < q; index++) {
      temp = scanner.nextLine();
      if (data.containsKey(temp) == true){
        System.out.println(data.get(temp));
      } else {
        System.out.println(0);
      }
    }
  }
}
