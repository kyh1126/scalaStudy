// src/main/java/progscala2/objectsystem/JavaArrays.java
package progscala2.objectsystem;

public class JavaArrays {
  public static void main(String[] args) {
    Integer[] array1 = new Integer[] {
      new Integer(1), new Integer(2), new Integer(3) };
    Number[] array2 = array1;      // 문제 없이 컴파일된다. 
    array2[2] = new Double(3.14);  // 문제 없이 컴파일되지만, 실행 시점에 오류가 발생한다!
  }
}
