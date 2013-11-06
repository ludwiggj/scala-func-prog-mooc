// 06 - map

object map {

  val numberList = List(9, -7, 2)

  // Double each member via map
  def double(x: Int) = { 2 * x }

  numberList map (double)

  numberList map (x => 2 * x)

  numberList map (2 * _)
}