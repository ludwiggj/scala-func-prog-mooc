package lectures.week6.combinatorial

object scalarProduct {

  type scalar = (Vector[Int], Vector[Int]) => Int

  def scalarProduct(xs: Vector[Int], ys: Vector[Int]): Int = {
    (xs zip ys).map(xy => xy._1 * xy._2).sum
  }

  def scalarProduct1(xs: Vector[Int], ys: Vector[Int]): Int = {
    (xs zip ys).map {
      case (x, y) => x * y
    }.sum
  }

  def scalarProduct2(xs: Vector[Int], ys: Vector[Int]): Int = {
    val sums = for {
      pos <- 0 until xs.size
    } yield (xs(pos) * ys(pos))
    println(sums)
    sums.sum
  }

  def scalarProduct3(xs: Vector[Int], ys: Vector[Int]): Int = {
    (for {
      (x, y) <- xs zip ys
    } yield (x * y))
      .sum
  }

  def test(f: scalar) = {
    val vector1To3: Vector[Int] = Vector(1, 2, 3)
    val vector4To6: Vector[Int] = Vector(4, 5, 6)

    println(f(vector1To3, vector4To6))
  }

  def main(args: Array[String]) {
    test(scalarProduct)
    test(scalarProduct1)
    test(scalarProduct2)
    test(scalarProduct3)
  }
}