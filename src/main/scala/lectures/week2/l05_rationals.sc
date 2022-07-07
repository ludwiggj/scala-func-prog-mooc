import lectures.week2.{Rational, RationalTake2}

object l05_rationals {
  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(6, 9)
  val whole = new Rational(5)
  x add y
  (x add y).neg
  x sub y
  x sub y sub z
  x less y
  y less x
  x max y max z
  y max z max x
  val xx = new RationalTake2(1, 3)
  val yy = new RationalTake2(5, 7)
  val zz = new RationalTake2(3, 2)
  xx + yy
  -(xx + yy)
  xx - yy
  xx - yy - zz
  yy + yy
  xx < yy
  yy < xx
  xx max yy max zz
  yy max zz max xx

  val two = new RationalTake2(2)
  -two
  -two < two
  two - -two
}