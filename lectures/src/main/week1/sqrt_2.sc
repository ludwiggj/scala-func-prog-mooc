object sqrt_2 {
  def abs(x: Double) = if (x < 0) -x else x

  def sqrt(x: Double) = {
    println("x [" + x + "] eps [" + 0.001 * x + "]")

    def isGoodEnough(guess: Double) = {
      abs(guess * guess - x) < (0.001 * x)
    }

    def improve(guess: Double) = {
      (guess + x / guess) / 2
    }

    def sqrtIter(guess: Double): Double = {
      if (isGoodEnough(guess)) {
        println("guess [" + guess + "] square [" + guess * guess + "] diff [" + abs(guess * guess - x) + "]")
        guess
      } else sqrtIter(improve(guess))
    }

    sqrtIter(1.0)
  }

  sqrt(2)



  sqrt(4)










  sqrt(1e-6)



}