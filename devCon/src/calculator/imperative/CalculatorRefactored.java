package calculator.imperative;

public class CalculatorRefactored {
  public static int sum(Functor f, int a, int b) {
    int total = 0;
    for (int x = a; x <= b; x++) {
      total += f.apply(x);
    }
    return total;
  }

  // Functor interface...
  static interface Functor {
    int apply(int x);
  }

  public static void main(String[] args) {
    System.out.println("sum(2 .. 4) => " + sum(new Functor() {
      @Override
      public int apply(int x) {
        return x;
      }
    }, 2, 4));

    System.out.println("sumOfSquares(2 .. 4) => " + sum(new Functor() {
      @Override
      public int apply(int x) {
        return x * x;
      }
    }, 2, 4));

    System.out.println("sumOfCubes(2 .. 4) => " + sum(new Functor() {
      @Override
      public int apply(int x) {
        return x * x * x;
      }
    }, 2, 4));
  }
}