package week4.poly

object doIt {
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T]);
                                                  //> singleton: [T](elem: T)week4.poly.Cons[T]
  def nth[T](n: Int, list: List[T]): T = {
    def nth[T](index: Int, n: Int, list: List[T]): T = {
      if (list.isEmpty) throw new IndexOutOfBoundsException("Index " + n + " is out of bounds (too large)!")
      if (index == n) list.head else nth(index + 1, n, list.tail)
    }
    if (n < 0) throw new IndexOutOfBoundsException("Index " + n + " is out of bounds (too small)!")
    nth(0, n, list)
  }                                               //> nth: [T](n: Int, list: week4.poly.List[T])T
  
  def nthh[T](n: Int, list: List[T]): T = {
    if ((n < 0) || (list.isEmpty)) throw new IndexOutOfBoundsException("Index is out of bounds!")
    if (n == 0) list.head else nthh(n-1, list.tail)
  }                                               //> nthh: [T](n: Int, list: week4.poly.List[T])T
  
  println("Hi")                                   //> Hi
  val x: Cons[Int] = new Cons[Int](1, new Nil[Int]);
                                                  //> x  : week4.poly.Cons[Int] = {1 | Nil}
  val y = singleton[Int](9)                       //> y  : week4.poly.Cons[Int] = {9 | Nil}
  val yy = singleton(10)                          //> yy  : week4.poly.Cons[Int] = {10 | Nil}
  val zz = singleton(true)                        //> zz  : week4.poly.Cons[Boolean] = {true | Nil}
  val z = new Cons[Int](4, y)                     //> z  : week4.poly.Cons[Int] = {4 | {9 | Nil}}
  
  nth(0, z)                                       //> res0: Int = 4
  nth(1, z)                                       //> res1: Int = 9
  //nth(-1, z)
  //nth(2, z)
  
  nthh(0, z)                                      //> res2: Int = 4
  nthh(1, z)                                      //> res3: Int = 9
  //nthh(-9, z)
  //nthh(9, z)
}

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  override def toString = "{" + head + " | " + tail + "}"
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  override def toString = "Nil"
}