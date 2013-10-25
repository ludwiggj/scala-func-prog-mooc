object squarer {

  type squarerType = List[Int] => List[Int]

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case y :: ys => (y * y) :: squareList(ys)
  }

  def squareList2(xs: List[Int]): List[Int] = {
      xs map (x => x * x)
    }

  def test(originalList: List[Int], squarer: squarerType) {
     println(originalList + " => " + squarer(originalList))
  }

  def main(args: Array[String]) = {
    test(List(9, 7, 2, -3, -5, 0, 22), squareList)
    test(List(9, 7, 2, -3, -5, 0, 22), squareList2)
  }
}