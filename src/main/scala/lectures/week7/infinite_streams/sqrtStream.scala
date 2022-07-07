package lectures.week7.infinite_streams

object sqrtStream {

  def abs(x: Double) = if (x < 0) -x else x

  def isGoodEnough(guess: Double, x: Double) = {
    abs(guess * guess - x) < (0.001 * x)
  }

  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }

  def main(args: Array[String]) {
    println(sqrtStream(2).take(10).toList)
    println(sqrtStream(2).filter(isGoodEnough(_, 2)).take(10).toList)
  }
}