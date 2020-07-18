// src/main/java/progscala2/fp/loops/FactorialEx5.java
package progscala2._6_fp.loops;

public class FactorialEx5 {
  public static long factorial(long l) {
    long result = 1L;
    for (long j = 2L; j <= l; j++) {
      result *= j;
    }
    return result;
  }

  public static void main(String args[]) {
    for (long l = 1L; l <= 10; l++)
      System.out.printf("%d:\t%d\n", l, factorial(l));
  }
}
