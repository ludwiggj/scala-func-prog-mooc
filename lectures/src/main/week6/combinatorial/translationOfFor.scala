object translationOfFor {
  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    for (x <- xs) yield f(x)

  def flatMap[T, U](xs: List[T], f: T => Iterable[U]): List[U] =
    for (x <- xs; y <- f(x)) yield y

  def main(args: Array[String]) {
    println(mapFun[Int, (Int, Int)]((1 to 5).toList, y => (y, y)))
    println(
      mapFun[Int, List[(Int, Int)]]((1 to 10).toList,
        x => mapFun[Int, (Int, Int)]((1 to 5).toList, y => (x, y)))
    )
    println(
      // Not sure about the type params for flatMap here
      flatMap[Int, (Int, Int)]((1 to 10).toList,
        x => mapFun[Int, (Int, Int)]((1 to 5).toList, y => (x, y)))
    )
  }
}