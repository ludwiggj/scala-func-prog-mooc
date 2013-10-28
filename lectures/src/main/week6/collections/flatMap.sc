object flatMap {
  def makeSubList(n: Int) = List(2 * n)

  val list = List(0, 1, 2, 3, 4, 5)
  list map makeSubList


  (list map makeSubList).flatten
  list flatMap makeSubList

  (1 to 10) flatMap (x => (1 to 10) map (y => (x, y)))










}
