/*
 * Created by IntelliJ IDEA.
 * User: 802940498
 * Date: 22/10/13
 * Time: 12:47
 */
package main.week4.covariance

class IntSet {
  override def toString = "IntSet"
}
class NonEmpty extends IntSet {
  override def toString = "NonEmpty"
}
class Empty extends IntSet {
  override def toString = "Empty"
}