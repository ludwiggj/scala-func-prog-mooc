package lectures.week2

class RationalTake2(x: Int, y: Int) {
  require(y > 0, "Denominator must be positive and cannot be 0")

  private def isNegative = numer < 0

  private def gcd(a: Int, b: Int): Int = {
    def abs(x: Int) = if (x < 0) -x else x
    def absGcd(absA: Int, absB: Int): Int = if (absB == 0) absA else absGcd(absB, absA % absB)
    absGcd(abs(a), abs(b))
  }

  private val g = gcd(x, y)
  private val numer = x / g;
  private val denom = y / g;

  def this(x: Int) = this(x, 1)

  def +(that: RationalTake2) =
    new RationalTake2(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def unary_- =
    new RationalTake2(-numer, denom)

  def -(that: RationalTake2) =
    this + -that

  def <(that: RationalTake2) =
    (this - that).isNegative

  def max(that: RationalTake2) =
    if (this < that) that else this

  override def toString = {
    if (denom == 1) numer.toString else numer + "/" + denom
  }
}