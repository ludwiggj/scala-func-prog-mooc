package imperative;

public class Point {
  private final int x;
  private final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "Point(x: " + x + ", y: " + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point)) return false;

    Point point = (Point) o;

    if (x != point.x) return false;
    if (y != point.y) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  public static void main(String[] args) {
    Point p_1_0 = new Point(1,0);
    Point p_1_2 = new Point(1,2);

    System.out.println(p_1_0);
    System.out.println(p_1_2);
    System.out.println("p_1_0 == p_1_2 ? [" + p_1_0.equals(p_1_2) + "]");
    System.out.println("p_1_0 == p_1_0 ? [" + p_1_0.equals(new Point(1,0)) + "]");
  }
}
