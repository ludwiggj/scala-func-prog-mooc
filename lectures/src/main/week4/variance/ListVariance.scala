package main.week4.variance

import main.week4.covariance._

object ListVariance {
  def main(args: Array[String]) {
    // +T is covariant, so List[Nothing] <: List[String]
    //                  because Nothing <: String

    val x: List[String] = Nil

    var list: List[NonEmpty] = new Concrete();
    println("List " + list)
    val prep1: IntSet = new IntSet
    println("About to prepend " + prep1)

    val list1: List[IntSet] = list.prepend(prep1)
    println("List " + list1)

    val prep2: NonEmpty = new NonEmpty
    println("About to prepend " + prep2)
    list = list.prepend(prep2)
    println("List " + list)


    val prep3: Empty = new Empty
    println("About to prepend " + prep3)
    var list2:List[IntSet] = list.prepend(prep3)
    println("List " + list2)
  }

  def f(xs: List[NonEmpty], x: Empty):List[IntSet] = xs prepend x
}

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  override def toString = "{" + head + " | " + tail + "}"
  //def prepend(elem: T): List[T] =new Cons(elem, this)
  def prepend[U >: T](elem: U): List[U] = {
    println("elem [" + elem + "] this [" + this + "]")
    new Cons(elem, this)
  }
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Concrete extends List[NonEmpty] {
  def head = new NonEmpty;
  def tail = null;
  def isEmpty = false;
}

object Nil extends List[Nothing] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  override def toString = "Nil"
}