object sqrt_1 {
  def abs(x: Double) = if (x < 0) -x else x

  def isGoodEnough(guess: Double, x: Double) = {
//    println("guess [" + guess + "] square [" + guess * guess + "] diff [" + abs(guess * guess - x) + "]")
    abs(guess * guess - x) < (0.001 * x)
  }

  def improve(guess: Double, x: Double) = {
    (guess + x / guess) / 2
  }

  def sqrtIter(guess: Double, x: Double, guessNumber: Int): Double = {
    if (isGoodEnough(guess, x)) {
      println("guess [" + guess + "] square [" + guess * guess + "] diff [" + abs(guess * guess - x) + "] no of guesses "
       + guessNumber + "]")
      guess
    }
    else sqrtIter(improve(guess, x), x, guessNumber + 1)
  }

  def sqrt(x: Double) = {
    println("x [" + x + "] eps [" + 0.001 * x + "]")
    sqrtIter(1.0, x, 1)
  }

  sqrt(2)
  sqrt(4)
  sqrt(0.001)
  sqrt(0.1e-20)
  sqrt(1.0e20)
  sqrt(1.0e50)
  sqrt(1e-6)
  sqrt(1e60)
}