// 10 Point and line pattern match

trait GeometricEntity
case class Point(x: Int, y: Int) extends GeometricEntity
case class Line(a: Point, b: Point) extends GeometricEntity
val p_1_0 = Point(1, 0)
val p_1_2 = Point(1, 2)
val p_0_5 = Point(0, 5)
val lineParallelToXAxis = Line(Point(1, 4), Point(5, 4))
val lineParallelToYAxis = Line(Point(1, 3), Point(1, 7))
val justALine = Line(Point(0, 0), Point(6, 6))
val whatAmI = (g: GeometricEntity) => g match {
  case Point(x, 0) => "A point on the x axis"
  case Point(0, y) => "A point on the y axis"
  case Point(_, _) => "A point out in the plain"
  case Line(Point(_, y1), Point(_, y2)) if (y1 == y2) => "A line parallel to the x axis"
  case Line(Point(x1, _), Point(x2, _)) if (x1 == x2) => "A line parallel to the y axis"
  case Line(_, _) => "A line not parallel to the x axis nor parallel to the y axis"
}
whatAmI(p_1_0)
whatAmI(p_1_2)
whatAmI(p_0_5)
whatAmI(lineParallelToXAxis)
whatAmI(lineParallelToYAxis)
whatAmI(justALine)







