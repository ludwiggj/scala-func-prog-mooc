package week4

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