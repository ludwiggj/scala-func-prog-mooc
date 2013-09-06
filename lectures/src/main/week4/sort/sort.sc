package week4.sort

object sort {
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, isort(ys))
  }                                               //> isort: (xs: List[Int])List[Int]
  
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x > y) y :: insert(x, ys) else x :: xs
  }                                               //> insert: (x: Int, xs: List[Int])List[Int]
  
  def testListSort(xs: List[Int]) {
    println("list = " + xs)
    println("sorted list = " + isort(xs))
  }                                               //> testListSort: (xs: List[Int])Unit
  
  testListSort(List(7, 3, 9, 2, 12, 4))           //> list = List(7, 3, 9, 2, 12, 4)
                                                  //| sorted list = List(2, 3, 4, 7, 9, 12)
  testListSort(List(0, 1, 2, 3, 4))               //> list = List(0, 1, 2, 3, 4)
                                                  //| sorted list = List(0, 1, 2, 3, 4)
  testListSort(List(0, -1, -2, -3, -4))           //> list = List(0, -1, -2, -3, -4)
                                                  //| sorted list = List(-4, -3, -2, -1, 0)
  testListSort(List(0))                           //> list = List(0)
                                                  //| sorted list = List(0)
  testListSort(List())                            //> list = List()
                                                  //| sorted list = List()
}