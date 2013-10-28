package week6.combinatorial

object nQueens {
  def queens(n: Int) = {
    def placeQueens(k: Int): Set[List[Int]] =
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe2(col, queens)
        } yield col :: queens
    placeQueens(n)
  }

  def isSafe(col: Int, queens: List[Int]): Boolean = {
    def pairSum = (p: (Int, Int)) => p._1 + p._2
    def pairDiff = (p: (Int, Int)) => p._1 - p._2

    def sameDiag(queens: Seq[(Int, Int)], proposed: (Int, Int), f: ((Int, Int)) => Int): Boolean = {
      val diagVal: Int = f(proposed)
      queens map (f(_)) exists (_ == diagVal)
    }

    // queens = (... col@row3, col@row2, col@row1)
    // row 0 queens(2)
    // row 1 queens(1)
    // row 2 queens(0)
    val currentQueens = for {
      row <- 0 until queens.size
    } yield (row, queens(queens.size - row - 1))

    val proposed = (queens.size, col)

    val colTaken = !queens.contains(col)
    val sameDiagTaken_direction_1 = !sameDiag(currentQueens, proposed, pairSum)
    val sameDiagTaken_direction_2 = !sameDiag(currentQueens, proposed, pairDiff)

    val safe = colTaken && sameDiagTaken_direction_1 && sameDiagTaken_direction_2
    safe
  }

  def isSafe2(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    val queensWithRow = (row - 1 to 0 by -1) zip queens
    queensWithRow forall {
      case (r, c) => col != c && math.abs(col - c) != row - r
    }
  }

  def show(queens: List[Int]) = {
    val lines =
      for (col <- queens.reverse)
      yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    (lines mkString "\n") + "\n"
  }

  def main(args: Array[String]) {
    val noOfQueens = 5
    println("Number of queens [" + noOfQueens + "]")
    println(queens(noOfQueens))
    queens(noOfQueens) map show foreach({
      println(_)
    })
  }
}