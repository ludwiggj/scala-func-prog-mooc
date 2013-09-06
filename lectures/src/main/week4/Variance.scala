package week4

class GParent

class Parent extends GParent

class Child extends Parent

class Box[+A]  // co-variant. If X <: Y, then Box[X] <: Box[Y]

class Box2[-A] // contra-variant. If X <: Y, then Box[X] >: Box[Y]

object Variance {
  def foo(x: Box[Parent]): Box[Parent] = identity(x)

  def bar(x: Box2[Parent]): Box2[Parent] = identity(x)

  foo(new Box[Child]) // success

  //foo(new Box[GParent]) // type error

  //bar(new Box2[Child]) // type error

  bar(new Box2[GParent]) // success
}