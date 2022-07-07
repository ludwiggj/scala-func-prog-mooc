package lectures.week2

class Rational(x: Int, y: Int) {
  require(y > 0, "Denominator must be positive and cannot be 0")
  private val numer = x;
  private val denom = y;

  // Auxiliary constructor
  def this(x: Int) = this(x, 1)

  private def isNegative = numer < 0

  private def abs(x: Int) = if (x < 0) -x else x

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def add(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def neg =
    new Rational(-numer, denom)

  def sub(that: Rational) =
    add(that.neg)

  def less(that: Rational) =
    this.sub(that).isNegative

  def max(that: Rational) =
    if (this.less(that)) that else this

  override def toString = {
    val g = gcd(abs(numer), abs(denom))
    val numer_gcd = numer / g
    val denom_gcd = denom / g
    if (denom_gcd == 1) numer_gcd.toString else numer_gcd + "/" + denom_gcd
  }
}