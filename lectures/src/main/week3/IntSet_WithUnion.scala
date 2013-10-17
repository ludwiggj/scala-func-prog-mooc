/*
 * Created by IntelliJ IDEA.
 * User: 802940498
 * Date: 16/10/13
 * Time: 22:22
 */
package main.week3

abstract class IntSet_WithUnion {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet_WithUnion
  def union(other: IntSet_WithUnion): IntSet_WithUnion
}

object Empty_WithUnion extends IntSet_WithUnion {
  def contains(x: Int) = false
  def incl(x: Int) = new NonEmpty_WithUnion(x, Empty_WithUnion, Empty_WithUnion)
  def union(other: IntSet_WithUnion) = {
    other
  }
  override def toString = "*"
}

class NonEmpty_WithUnion(elem:Int, left: IntSet_WithUnion, right: IntSet_WithUnion) extends IntSet_WithUnion {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet_WithUnion =
    if (x < elem) new NonEmpty_WithUnion(elem, left incl x, right)
    else if (x > elem) new NonEmpty_WithUnion(elem, left, right incl x)
    else this

  def union(other: IntSet_WithUnion) = {
    left union (right union (other incl elem))
  }

  override def toString = "<" + left + "[" + elem + "]" + right + ">"
}