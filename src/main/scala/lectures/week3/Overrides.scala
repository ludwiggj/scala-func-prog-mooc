package lectures.week3

abstract class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
 override def foo = 7
 def bar = 1
}