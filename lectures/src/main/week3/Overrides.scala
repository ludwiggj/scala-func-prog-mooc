/*
 * Created by IntelliJ IDEA.
 * User: 802940498
 * Date: 16/10/13
 * Time: 23:12
 */
package main.week3

abstract class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
 override def foo = 7
 def bar = 1
}