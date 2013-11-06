// bonus - Point and line pattern match

trait GeometricEntity

case class GeometricPoint(x: Int, y: Int) extends GeometricEntity

case class GeometricLine(a:  GeometricPoint, b:  GeometricPoint) extends GeometricEntity

object pointAndLinePatternMatch {
  val whatAmI = (g: GeometricEntity) => g match {
    case GeometricPoint(x, 0)
        => "A point on the x axis"

    case GeometricPoint(0, y)
        => "A point on the y axis"

    case GeometricPoint(_, _)
        => "A point out in the plain"

    case GeometricLine(GeometricPoint(_, y1), GeometricPoint(_, y2)) if (y1 == y2)
        => "A line parallel to the x axis"

    case GeometricLine(GeometricPoint(x1, _), GeometricPoint(x2, _)) if (x1 == x2)
        => "A line parallel to the y axis"

    case GeometricLine(_, _)
        => "A line not parallel to the x axis nor parallel to the y axis"
  }                                               //> whatAmI  : GeometricEntity => String = <function1>

  whatAmI(GeometricPoint(1, 0))                   //> res0: String = A point on the x axis

  whatAmI(GeometricPoint(1, 2))                   //> res1: String = A point out in the plain

  whatAmI(GeometricPoint(0 , 5))                  //> res2: String = A point on the y axis

  whatAmI(GeometricLine(GeometricPoint(1, 4),
                        GeometricPoint(5, 4)))    //> res3: String = A line parallel to the x axis

  whatAmI(GeometricLine(GeometricPoint(1, 3),
                        GeometricPoint(1, 7)))    //> res4: String = A line parallel to the y axis

  whatAmI(GeometricLine(GeometricPoint(0, 0),
                        GeometricPoint(6, 6)))    //> res5: String = A line not parallel to the x axis nor parallel to the y axis
                                                  //|
}