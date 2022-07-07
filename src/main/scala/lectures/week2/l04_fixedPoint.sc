object l04_fixedPoint {
  val tolerance = 0.0001

  def abs(x:Double) =
    if (x > 0) x else -x

  def isCloseEnough(x: Double, y:Double) =
    abs((x-y)/x) / x < tolerance;

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      println("guess [" + guess + "]")
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x))/2

  def sqrt(x: Double) =
    fixedPoint(averageDamp(y => x/y))(1)

  def sqrt_2(x: Double) =
    fixedPoint(y => (y + x/y)/2)(1)

  fixedPoint(x => 1 + x/2)(1)














  sqrt(2)




}