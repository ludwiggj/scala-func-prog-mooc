// 07 - groupBy and foldLeft

object groupByAndFoldLeft {

  val sesameStreet: List[String] =
    List("Ernie", "lives", "on", "Sesame", "Street", "with", "his", "friend", "Burt")

  sesameStreet groupBy (_.length)

  sesameStreet groupBy (_.toLowerCase.head)

  // foldLeft(acc)(List(x1, ..., xn))(op) = ( ... (acc op x1) op ... ) op xn

  val numbers = List(1, 2, 3, 4, 5)

  // Sum of numbers 1 to 5 ...
  // = 0 + 1 + 2 + 3 + 4 + 5
  val sum = numbers.foldLeft(0)(_ + _)

  // Product of numbers 1 to 5 ...
  // 1 * 1 * 2 * 3 * 4 * 5
  val product = numbers.foldLeft(1)(_ * _)
}