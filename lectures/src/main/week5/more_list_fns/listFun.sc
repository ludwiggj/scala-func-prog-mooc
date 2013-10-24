import annotation.tailrec

object listFun {
  def last[T](xs: List[T]): T = xs match {
    case List() => throw new Error("last of an empty list")
    case List(x) => x
    case y :: ys => last(ys)
  }

  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of an empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case z :: zs => z :: concat(zs, ys)
  }

  def reverse[T](xs: List[T]): List[T] = xs match {
    case List() => xs
    case y :: ys => reverse(ys) ++ List(y)
  }

  def removeAt[T](n: Int, xs: List[T]) = {
    def removeAt(pos: Int, ys: List[T], acc: List[T]): List[T] =
      ys match {
        case List() => acc.reverse
        case z :: zs => if (pos == n) removeAt(pos + 1, zs, acc)
        else removeAt(pos + 1, zs, z :: acc)
      }
    removeAt(0, xs, List())
  }

  def removeAt2[T](n: Int, xs: List[T]) = {
    def removeAt(pos: Int, ys: List[T], acc: List[T]): List[T] =
      ys match {
        case List() => acc
        case z :: zs => if (pos == n) removeAt(pos + 1, zs, acc)
        else removeAt(pos + 1, zs, acc ++ List(z))
      }
    removeAt(0, xs, List())
  }
  def removeAt3[T](n: Int, xs: List[T]) = {
    (xs take n) ::: (xs drop n + 1)
  }
  def testRemoveAt[T](pos: Int, list: List[T]): List[List[T]] = {
    removeAt(pos, list) :: removeAt2(pos, list) :: removeAt3(pos, list) :: Nil
  }
  last(List(1))
  last(List("a", "b", "c"))
  init(List(1))
  init(List("a", "b", "c"))
  concat(List(), List())
  concat(List(1), List())
  concat(List(), List(2))
  concat(List(3, 4), List(5, 6, 7))
  reverse(List())
  reverse(List(1))
  reverse(List(1, 2, 3))
  testRemoveAt(-1, List('a', 'b', 'c', 'd'))

  testRemoveAt(0, List('a', 'b', 'c', 'd'))
  testRemoveAt(1, List('a', 'b', 'c', 'd'))
  testRemoveAt(2, List('a', 'b', 'c', 'd'))
  testRemoveAt(3, List('a', 'b', 'c', 'd'))
  testRemoveAt(4, List('a', 'b', 'c', 'd'))

  testRemoveAt(5, List('a', 'b', 'c', 'd'))

  testRemoveAt(6, List('a', 'b', 'c', 'd'))

}