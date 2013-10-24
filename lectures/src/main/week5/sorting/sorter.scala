object sorter {
  type MergeAlgorithm[T] = (List[T], List[T]) => List[T]

  def msort[T](xs: List[T], merge: MergeAlgorithm[T]): List[T] = {
    def sort(xs: List[T]): List[T] = {
      val n = xs.length / 2
      if (n == 0) xs
      else {
        val (first, second) = xs splitAt n
        merge(sort(first), sort(second))
      }
    }
    sort(xs)
  }

  def merge(xs: List[Int], ys: List[Int]): List[Int] =
    xs match {
      case Nil => ys
      case x :: xs1 =>
        ys match {
          case Nil => xs
          case y :: ys1 =>
            if (x < y) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
        }
    }

  def symmetricalMerge(xs: List[Int], ys: List[Int]): List[Int] =
    (xs, ys) match {
      case (xs, Nil) => xs
      case (Nil, ys) => ys
      case (x :: xs1, y :: ys1) =>
        if (x < y) x :: symmetricalMerge(xs1, ys)
        else y :: symmetricalMerge(xs, ys1)
    }

  def genericMerge[T](xs: List[T], ys: List[T])(lt: (T, T) => Boolean): List[T] =
    (xs, ys) match {
      case (xs, Nil) => xs
      case (Nil, ys) => ys
      case (x :: xs1, y :: ys1) =>
        if (lt(x, y)) x :: genericMerge(xs1, ys)(lt)
        else y :: genericMerge(xs, ys1)(lt)
    }

  def wholeShebang[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def doMerge(xs: List[T], ys: List[T]): List[T] = {
        (xs, ys) match {
          case (xs, Nil) => xs
          case (Nil, ys) => ys
          case (x :: xs1, y :: ys1) =>
            if (ord.lt(x, y)) x :: doMerge(xs1, ys)
            else y :: doMerge(xs, ys1)
        }
      }
      val (first, second) = xs splitAt n
      doMerge(wholeShebang(first), wholeShebang(second))
    }
  }

  def testMerge[T](mergeAlgorithm: String, originalList: List[T], sortedList: List[T]) {
    println(mergeAlgorithm + ": " + originalList + " => " + sortedList)
  }

  def main(args: Array[String]) = {
    val list1 = List(5, 1, -9, 26, 24, 37, 80)
    val list2 = List(1)
    val list3 = List()
    val list4 = List(1, 2)
    val list5 = List(2, 1)
    val list1Str = List("5", "1", "-9", "26", "24", "37", "80")
    val fruit = List("cherry", "apple", "pear", "tin o peaches", "orange", "pineapple")
    val listF: List[Float] = List(5.0f, 1.0f, -9.0f, 26.0f, 24.0f, 37.0f, 80.0f)

    testMerge("merge", list1, msort(list1, merge))
    testMerge("merge", list2, msort(list2, merge))
    testMerge("merge", list3, msort(list3, merge))
    testMerge("merge", list4, msort(list4, merge))
    testMerge("merge", list5, msort(list5, merge))

    testMerge("symmetricalMerge", list1, msort(list1, symmetricalMerge))
    testMerge("symmetricalMerge", list2, msort(list2, symmetricalMerge))
    testMerge("symmetricalMerge", list3, msort(list3, symmetricalMerge))
    testMerge("symmetricalMerge", list4, msort(list4, symmetricalMerge))
    testMerge("symmetricalMerge", list5, msort(list5, symmetricalMerge))

    val intCompare = (a: Int, b: Int) => a > b
    val integerMerge = genericMerge(_: List[Int], _: List[Int])(intCompare)
    testMerge("integerMerge", list1, msort(list1, integerMerge))
    testMerge("integerMerge", list2, msort(list2, integerMerge))
    testMerge("integerMerge", list3, msort(list3, integerMerge))
    testMerge("integerMerge", list4, msort(list4, integerMerge))
    testMerge("integerMerge", list5, msort(list5, integerMerge))

    val stringCompare = (a: String, b: String) => a > b
    val stringMerge = genericMerge(_: List[String], _: List[String])(stringCompare)
    testMerge("stringMerge", list1Str, msort(list1Str, stringMerge))
    testMerge("stringMerge", fruit, msort(fruit, stringMerge))

    val floatCompare = ((a: Float, b: Float) => a > b)
    val floatyMerge = genericMerge(_: List[Float], _: List[Float])(floatCompare)
    testMerge("floatyMerge", listF, msort(listF, floatyMerge))

    testMerge("wholeShebang", listF, wholeShebang(listF)(Ordering.Float))
    testMerge("wholeShebang", list1, wholeShebang(list1)(Ordering.Int))
    testMerge("wholeShebang", list1Str, wholeShebang(list1Str)(Ordering.String))
    testMerge("wholeShebang", listF, wholeShebang(listF))
    testMerge("wholeShebang", list1, wholeShebang(list1))
    testMerge("wholeShebang", list1Str, wholeShebang(list1Str))
  }
}