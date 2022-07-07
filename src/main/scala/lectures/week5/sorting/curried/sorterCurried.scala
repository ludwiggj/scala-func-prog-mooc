package lectures.week5.sorting.curried

object sorterCurried {
  type MergeAlgorithm[T] = (List[T], List[T]) => List[T]

  def mergesortCurried[T](merge: MergeAlgorithm[T]): List[T] => List[T] = {
    def sort(xs: List[T]): List[T] = {
      val n = xs.length / 2
      if (n == 0) xs
      else {
        val (first, second) = xs splitAt n
        merge(sort(first), sort(second))
      }
    }
    sort
  }

  def basicMerge(xs: List[Int], ys: List[Int]): List[Int] =
    xs match {
      case Nil => ys
      case x :: xs1 =>
        ys match {
          case Nil => xs
          case y :: ys1 =>
            if (x < y) x :: basicMerge(xs1, ys)
            else y :: basicMerge(xs, ys1)
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

  def genericMergeCurried[T](lt: (T, T) => Boolean): MergeAlgorithm[T] = {
    def genericMerge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (xs, Nil) => xs
        case (Nil, ys) => ys
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: genericMerge(xs1, ys)
          else y :: genericMerge(xs, ys1)
      }
    }
    genericMerge
  }

  def genericMergeOrderedCurried[T](implicit ord: Ordering[T]): MergeAlgorithm[T] = {
      def genericMerge(xs: List[T], ys: List[T]): List[T] = {
        (xs, ys) match {
          case (xs, Nil) => xs
          case (Nil, ys) => ys
          case (x :: xs1, y :: ys1) =>
            if (ord.lt(x, y)) x :: genericMerge(xs1, ys)
            else y :: genericMerge(xs, ys1)
        }
      }
      genericMerge
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
    val listF = List(5.0f, 1.0f, -9.0f, 26.0f, 24.0f, 37.0f, 80.0f)

    def mergeSort_BasicMerge = mergesortCurried(basicMerge)

    testMerge("merge", list1, mergeSort_BasicMerge(list1))
    testMerge("merge", list2, mergeSort_BasicMerge(list2))
    testMerge("merge", list3, mergeSort_BasicMerge(list3))
    testMerge("merge", list4, mergeSort_BasicMerge(list4))
    testMerge("merge", list5, mergeSort_BasicMerge(list5))

    def mergeSort_SymmetricalMerge = mergesortCurried(symmetricalMerge)

    testMerge("symmetricalMerge", list1, mergeSort_SymmetricalMerge(list1))
    testMerge("symmetricalMerge", list2, mergeSort_SymmetricalMerge(list2))
    testMerge("symmetricalMerge", list3, mergeSort_SymmetricalMerge(list3))
    testMerge("symmetricalMerge", list4, mergeSort_SymmetricalMerge(list4))
    testMerge("symmetricalMerge", list5, mergeSort_SymmetricalMerge(list5))

    val intCompare = (a: Int, b: Int) => a > b
    def genericMerge_Integer = genericMergeCurried(intCompare)
    def mergeSort_SymmetricalMerge_Integer = mergesortCurried(genericMerge_Integer)

    testMerge("integerMerge", list1, mergeSort_SymmetricalMerge_Integer(list1))
    testMerge("integerMerge", list2, mergeSort_SymmetricalMerge_Integer(list2))
    testMerge("integerMerge", list3, mergeSort_SymmetricalMerge_Integer(list3))
    testMerge("integerMerge", list4, mergeSort_SymmetricalMerge_Integer(list4))
    testMerge("integerMerge", list5, mergeSort_SymmetricalMerge_Integer(list5))

    val stringCompare = (a: String, b: String) => a > b
    def genericMerge_String = genericMergeCurried(stringCompare)
    def mergeSort_SymmetricalMerge_String = mergesortCurried(genericMerge_String)

    testMerge("stringMerge", list1Str, mergeSort_SymmetricalMerge_String(list1Str))
    testMerge("stringMerge", fruit, mergeSort_SymmetricalMerge_String(fruit))

    val floatCompare = (a: Float, b: Float) => a > b
    def genericMerge_Float = genericMergeCurried(floatCompare)
    def mergeSort_SymmetricalMerge_Float = mergesortCurried(genericMerge_Float)

    testMerge("floatyMerge", listF, mergeSort_SymmetricalMerge_Float(listF))
    testMerge("floatyMerge", listF, mergesortCurried(genericMergeOrderedCurried(Ordering[Float]))(listF))

    // Now using implicit feature
    testMerge("integerMerge", list1, mergesortCurried(genericMergeOrderedCurried[Int])(list1))
    testMerge("stringMerge", fruit, mergesortCurried(genericMergeOrderedCurried[String])(fruit))
    testMerge("floatyMerge", listF, mergesortCurried(genericMergeOrderedCurried[Float])(listF))
  }
}