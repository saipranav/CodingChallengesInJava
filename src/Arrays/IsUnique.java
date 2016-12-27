package Arrays;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by Sai Pranav on 12/20/2016.
 */

public class IsUnique {
  public static void main(String args[]){
    Person[] persons  = new Person[10];
    for(int i = 0; i < 10; i++){
      Person person = new Person(Integer.toString(1));
      persons[i] = person;
    }

    System.out.println(isUnique(persons));
  }

  public static <T> boolean isUnique(T[] array) {
    Set<T> set = new HashSet<T>();

    for(T element : array){
      if (set.contains(element) == false) {
        set.add(element);
      } else {
        return false;
      }
    }

    return true;
  }
}

class Person {
  String name;
  public Person(String name){
    this.name = name;
  }
  public boolean equals( Object b ){
    return this.name.equals(((Person) b).name);
  }
  public int hashCode() {
    return this.name.hashCode();
  }
}