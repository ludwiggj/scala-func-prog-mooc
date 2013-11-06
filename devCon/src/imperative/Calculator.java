package imperative;

public class Calculator {
  public static int sum(int a, int b) {
    int total = 0;
    for (int x = a; x <= b; x++) {
      total += x;                                    // This is the only line that differs between the methods
    }
    return total;
  }

  public static int sumOfSquares(int a, int b) {
    int total = 0;
    for (int x = a; x <= b; x++) {
      total += x * x;                                // This is the only line that differs between the methods
    }
    return total;
  }

  public static int sumOfCubes(int a, int b) {
    int total = 0;
    for (int x = a; x <= b; x++) {
      total += x * x * x;                          // This is the only line that differs between the methods
    }
    return total;
  }

  public static void main(String[] args) {
    System.out.println("sum(2 .. 4) => " + sum(2, 4));
    System.out.println("sumOfSquares(2 .. 4) => " + sumOfSquares(2, 4));
    System.out.println("sumOfCubes(2 .. 4) => " + sumOfCubes(2, 4));
  }
}
