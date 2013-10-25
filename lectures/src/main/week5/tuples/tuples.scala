package main.week5.tuples

import scala.Predef._

object tuples {
  def main(args: Array[String]) = {
    val pair = ("answer", 42)
    val (x, y) = pair
    println(x)
    println(y)
    println(pair._1)
    println(pair._2)
    val l: List[(String, Int)] = List(("orange", 10), ("pineapple", 45), ("plum", 88), ("grape", 4), ("banana", 24), ("grapefruit", 3))

    val (firstHalf, secondHalf) = l splitAt (l.length/2)
    println(firstHalf)
    println(secondHalf)
  }
}