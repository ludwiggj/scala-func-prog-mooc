object folder {
  type ListReducer = (List[Int]) => Int

  def sum_L(xs: List[Int]) = reduceLeft(0 :: xs)((x, y) => x + y)

  def sum_L1(xs: List[Int]) = reduceLeft(0 :: xs)(_ + _)

  def sum_L2(xs: List[Int]) = foldLeft(0)(xs)(_ + _)

  def sum_R(xs: List[Int]) = reduceRight(0 :: xs)((x, y) => x + y)

  def sum_R1(xs: List[Int]) = reduceRight(0 :: xs)(_ + _)

  def sum_R2(xs: List[Int]) = foldRight(0)(xs)(_ + _)

  def product_L(xs: List[Int]) = reduceLeft(1 :: xs)((x, y) => x * y)

  def product_L1(xs: List[Int]) = reduceLeft(1 :: xs)(_ * _)

  def product_L2(xs: List[Int]) = foldLeft(1)(xs)(_ * _)

  def product_R(xs: List[Int]) = reduceRight(1 :: xs)((x, y) => x * y)

  def product_R1(xs: List[Int]) = reduceRight(1 :: xs)(_ * _)

  def product_R2(xs: List[Int]) = foldRight(1)(xs)(_ * _)

  def reverse[T](xs: List[T]): List[T] =
    foldLeft (List[T]())(xs)((xs, x) => x :: xs)

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    foldRight (List[U]())(xs)((x, xs) => f(x) :: xs)

  def lengthFun[T](xs: List[T]): Int =
    foldRight (0)(xs)((x, y) => y + 1)

  def reduceLeft[T](list: List[T])(op: (T, T) => T): T = list match {
    case Nil => throw new Error("Nil.reduceLeft")
    case x :: xs => foldLeft(x)(xs)(op)
  }

  def foldLeft[T, U](z: U)(list: List[T])(op: (U, T) => U): U = list match {
    case Nil => z
    case x :: xs => foldLeft(op(z, x))(xs)(op)
  }

  def reduceRight[T](list: List[T])(op: (T, T) => T): T = list match {
    case Nil => throw new Error("Nil.reduceLeft")
    case x :: Nil => x
    case x :: xs => op(x, reduceRight(xs)(op))
  }

  def foldRight[T, U](z: U)(list: List[T])(op: (T, U) => U): U = list match {
    case Nil => z
    case x :: xs => op(x, foldRight(z)(xs)(op))
  }

  // Following version of concat works...
  //
  // foldRight(acc)(List(x1, ..., xn))(op) = x1 op ( ... ( xn op acc ) ... )
  //
  // Which translates into...
  //
  // x1 :: x2 :: ... :: xn :: acc
  //
  // where x1..n are list elements and acc is a list
  //
  def concat_R[T](xs: List[T], ys: List[T]): List[T] =
    foldRight(ys)(xs)(_ :: _)

  // Following version of concat does not compile
  //
  // foldLeft(z)(List(x1, ..., xn))(op) = ( ... (z op x1) op ... ) op xn
  //
  // Which translates into...
  //
  // acc :: x1 :: x2 :: ... :: xn
  //
  // where x1..n are list elements and acc is a list
  //
  // This doesn't compile, as the :: operator is right-associative,
  // and x1 is a list element (and integer in this case), hence the
  // compilation error...
  //
  //  value :: is not a member of type parameter Int
  //
  def concat_L[Int](xs: List[Int], ys: List[Int]): List[Int] =
//    foldLeft(xs)(ys)(_ :: _) // Does not work!
      List()

  def test(opName: String, testData: List[List[Int]], reducers: List[ListReducer]) {
    testData.foreach(list => {
      reducers.foreach(reduce => {
        println(opName + "(" + list + ") => " + reduce(list))
      }
      )
    }
    )
  }

  def main(args: Array[String]) = {
    val testData = List(List(), List(7), List(2, 4, 8))
    val reduceByAdding: List[ListReducer] = List(sum_L, sum_L1, sum_L2, sum_R, sum_R1, sum_R2)
    val reduceByProduct: List[ListReducer] = List(product_L, product_L1, product_L2, product_R, product_R1, product_R2)

    println(sum_L(List(1, 2, 3)))
    println(sum_R(List(1, 2, 3)))

    test("sum", testData, reduceByAdding)
    test("product", testData, reduceByProduct)

    println((List[Int]() foldLeft (1))(_ * _))

    val l = List[Int]()
    println(l.foldLeft(1)(_ * _)) // OK
    println((l foldLeft 1)(_ * _)) // OK

    //    (l foldLeft (1)(_ * _)) // NOT OK

    println(reduceLeft(List(2, 4, 8))(_ + _))
    println(foldLeft(1)(List(2, 4, 8))(_ * _))

    println(reduceRight(List(20, 40, 80))(_ + _))
    println(foldRight(2)(List(2, 4, 8))(_ * _))

    println(concat_R(List(1, 2, 3), List(4, 5, 6)))
    println(concat_L(List(1, 2, 3), List(4, 5, 6)))

    println(reverse(List(9,8,7,6,5,4,3,2,1)))

    def intToTuple(x: Int): (Int, Int) = (x, x)

    println(mapFun(List(5,3,1), intToTuple))
    println(lengthFun(List(5,3,"1, 6, 5", "6")))
  }
}