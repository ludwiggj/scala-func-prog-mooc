package week3

object rationals {
  val x = new Rational(1, 3)                      //> x  : week3.rationals.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : week3.rationals.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week3.rationals.Rational = 3/2
  x.add(y)                                        //> res0: week3.rationals.Rational = 22/21
  x.add(y).neg                                    //> res1: week3.rationals.Rational = -22/21
  x.sub(y)                                        //> res2: week3.rationals.Rational = -8/21
  x.sub(y).sub(z)                                 //> res3: week3.rationals.Rational = -79/42
  y.add(y)                                        //> res4: week3.rationals.Rational = 10/7
  x.less(y)                                       //> res5: Boolean = true
  y.less(x)                                       //> res6: Boolean = false
  x.max(y).max(z)                                 //> res7: week3.rationals.Rational = 3/2
  y.max(z).max(z)                                 //> res8: week3.rationals.Rational = 3/2
  //new Rational(2,0)
  new Rational(2)                                 //> res9: week3.rationals.Rational = 2/1
  
  val xx = new BetterRational(1, 3)               //> xx  : week3.rationals.BetterRational = 1/3
  val yy = new BetterRational(5, 7)               //> yy  : week3.rationals.BetterRational = 5/7
  val zz = new BetterRational(3, 2)               //> zz  : week3.rationals.BetterRational = 3/2
  xx + yy                                         //> res10: week3.rationals.BetterRational = 22/21
  -(xx + yy)                                      //> res11: week3.rationals.BetterRational = -22/21
  xx - yy                                         //> res12: week3.rationals.BetterRational = -8/21
  xx - yy - zz                                    //> res13: week3.rationals.BetterRational = -79/42
  yy + yy                                         //> res14: week3.rationals.BetterRational = 10/7
  xx < yy                                         //> res15: Boolean = true
  yy < xx                                         //> res16: Boolean = false
  xx max yy max zz                                //> res17: week3.rationals.BetterRational = 3/2
  yy max zz max xx                                //> res18: week3.rationals.BetterRational = 3/2
  //new BetterRational(2,0)
  new BetterRational(2)                           //> res19: week3.rationals.BetterRational = 2/1
                                                  
  
  class Rational(x: Int, y: Int) {
    require(y > 0, "Denominator must be positive and cannot be 0")
    private def isNegative = numer < 0
    private def abs(x: Int) = if (x < 0) -x else x
    private def gcd(a: Int, b: Int):Int = if (b == 0) a else gcd(b, a % b)
    
    //private val g = gcd(abs(x), abs(y))
    //private val numer = x / g;
    //private val denom = y / g;
    
    private val numer = x;
    private val denom = y;
    
    def this(x: Int) = this(x, 1)
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
      
    //override def toString = numer + "/" +denom
    override def toString = {
      val g = gcd(abs(numer), abs(denom))
      numer/g + "/" + denom/g
    }
  }
  
  class BetterRational(x: Int, y: Int) {
    require(y > 0, "Denominator must be positive and cannot be 0")
    
    private def isNegative = numer < 0
    
    private def gcd(a: Int, b: Int):Int = {
      def abs(x: Int) = if (x < 0) -x else x
      def absGcd(absA: Int, absB: Int): Int = if (absB == 0) absA else absGcd(absB, absA % absB)
      absGcd(abs(a), abs(b))
    }
    
    private val g = gcd(x, y)
    private val numer = x / g;
    private val denom = y / g;
    
    def this(x: Int) = this(x, 1)
    
    def +(that: BetterRational) =
      new BetterRational(
        numer * that.denom + that.numer * denom,
        denom * that.denom)
        
    def unary_- =
      new BetterRational(-numer, denom)
        
    def -(that: BetterRational) =
      this + -that
      
    def <(that: BetterRational) =
      (this - that).isNegative
      
    def max(that: BetterRational) =
      if (this < that) that else this
      
    override def toString = {
      numer + "/" + denom
    }
  }
}