package lectures.week5.higher_order_fns

import scala.language.postfixOps

object packer {
  val data = List("a", "a", "a", "b", "c", "c", "a")

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 => List(x :: xs1 takeWhile (y => y == x)) ++ pack(xs1 dropWhile (y => y == x))
  }

  def packNicer[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, second) = xs span (y => y == x)
      first :: packNicer(second)
  }

  def encode[T](xs: List[T]): List[(T, Int)] = {
    packNicer(xs) map (x => (x head, x length))
  }


  def main(args: Array[String]) {
    println(List(List(1 :: List(2)), List(2)))
    println(List(List(1)) ++ List(List(2)))
    println(pack(data))
    println(packNicer(data))
    println(encode(data))
  }
}