package lectures.week3

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

object AList {
  def apply[T]() = new Nil

  def apply[T](x1: T): List[T] = new Cons(x1, new Nil);

  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))

  def apply[T](x1: T, x2: T, x3: T): String = "Hey! This object was passed arguments [" + x1 + "] [" + x2 + "] and [" + x3 + "]!"

  def main(args: Array[String]) {
    println("Hello, world!")
    println(AList())
    println(AList(1))
    println(AList(2, 3))
    println(AList(4, 5, 6))
    println(AList(5))
  }
}

object BList {
   def List() = new Nil
   def List(a: Int) = new Cons(a, new Nil)
   def List(a: Int, b: Int) = new Cons(a, new Cons(b, new Nil))
}

object Singleton {
  def create[T](elem: T) = new Cons[T](elem, new Nil[T]);
}