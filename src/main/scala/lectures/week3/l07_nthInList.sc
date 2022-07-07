import lectures.week3._

object main {
  def nth[T](n: Int, list: List[T]): T = {
    def nth[T](index: Int, n: Int, list: List[T]): T = {
      if (list.isEmpty) throw new IndexOutOfBoundsException("Index " + n + " is out of bounds (too large)!")
      if (index == n) list.head else nth(index + 1, n, list.tail)
    }
    if (n < 0) throw new IndexOutOfBoundsException("Index " + n + " is out of bounds (too small)!")
    nth(0, n, list)
  }

  def nth2[T](n: Int, list: List[T]): T = {
    if ((n < 0) || (list.isEmpty)) throw new IndexOutOfBoundsException("Index " + n + " is out of bounds!")
    if (n == 0) list.head else nth2(n - 1, list.tail)
  }

  val y = Singleton.create[Int](9)
  val z = new Cons[Int](4, y)

  nth(0, z)
  nth(1, z)
  nth(2, new Nil[Int])








































  nth(-1, z)







































  nth(2, z)








































  nth2(0, z)
  nth2(1, z)
  nth2(1, new Nil[Int])







































  //nthh(-9, z)
  //nthh(9, z)
}