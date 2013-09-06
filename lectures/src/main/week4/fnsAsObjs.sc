package week4.fnAsObj

/*
object fnAsObj {
  def List() = new Nil
  def List(a: Int) = new Cons(a, new Nil)
  //def List(a: Int, b: Int) = new Cons(a, new Cons(b, new Nil))
  
  println("Welcome to the Scala worksheet")
}*/

class fnAsObj {
  def List() = new Nil
  def List(a: Int) = new Cons(a, new Nil)
  def List(a: Int, b: Int) = new Cons(a, new Cons(b, new Nil))
  
  println("Welcome to the Scala worksheet")
}
 

object fnAsObj {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  new fnAsObj().List()                            //> Welcome to the Scala worksheet
                                                  //| res0: week4.fnAsObj.Nil[Nothing] = Nil
  new fnAsObj().List(1)                           //> Welcome to the Scala worksheet
                                                  //| res1: week4.fnAsObj.Cons[Int] = {1 | Nil}
  new fnAsObj().List(1, 2)                        //> Welcome to the Scala worksheet
                                                  //| res2: week4.fnAsObj.Cons[Int] = {1 | {2 | Nil}}
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