/*
 * Created by IntelliJ IDEA.
 * User: 802940498
 * Date: 22/10/13
 * Time: 00:54
 */
package main.week4.fns_as_objs

import scala._

class FnAsObj {
  def List() = new Nil
  def List(a: Int) = new Cons(a, new Nil)
  def List(a: Int, b: Int) = new Cons(a, new Cons(b, new Nil))
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