// 09 Point pattern match

case class Point(x: Int, y: Int)

object pointPatternMatch {
  val p_1_0 = Point(1, 0)

  val p_1_2 = Point(1, 2)

  "p_1_0 == p_1_2 ? [" + (p_1_0 == p_1_2) + "]"

  "p_1_0 == p_1_2 ? [" + (p_1_0 == Point(1, 0)) + "]"


  val whereami = (p: Point) => p match {
    case Point(x, 0) => "On the x axis"
    case Point(0, y) => "On the y axis"
    case _ => "Out in the plain"
  }

  whereami(p_1_0)

  whereami(p_1_2)

  whereami(Point(0, 5))
}