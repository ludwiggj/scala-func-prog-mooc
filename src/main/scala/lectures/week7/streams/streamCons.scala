package lectures.week7.streams

import scala.language.postfixOps

object streamCons {
  def main(args: Array[String]) {
    // error: value :: is not a member of scala.collection
    //  1 :: Stream(2, 3, 4)
    val x = Stream(2, 3, 4)
    val y = 1 #:: x
    val z = 0 #:: y

    println(x)
    println(y)
    println(z)
    println(x toList)
    println(y)
    println(z)
  }
}