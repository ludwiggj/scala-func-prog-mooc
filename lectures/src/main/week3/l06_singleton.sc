import main.week3._

object main {
  val x: Cons[Int] = new Cons[Int](1, new Nil[Int]);
  val y = Singleton.create[Int](9)
  val yy = Singleton.create(10)
  val zz = Singleton.create(true)
  val z = new Cons[Int](4, y)
}