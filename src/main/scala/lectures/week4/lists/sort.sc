object sort {
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x > y) y :: insert(x, ys) else x :: xs
  }
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, isort(ys))
  }
  def testListSort(xs: List[Int]) {
    println("list = [" + xs + "] sorted = [" + isort(xs) + "]")
  }
  testListSort(List(7, 3, 9, 2, 12, 4))

  testListSort(List(0, 1, 2, 3, 4))
  testListSort(List(0, -1, -2, -3, -4))
  testListSort(List(0))
  testListSort(List())
}