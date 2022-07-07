package exercises.week1.recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    @tailrec
    def innerTriangleRow(acc: List[Int], previousRow: List[Int]): List[Int] = {
      if (previousRow.size < 2) {
        acc
      } else {
        innerTriangleRow(acc :+ (previousRow(0) + previousRow(1)), previousRow.tail)
      }
    }

    @tailrec
    def findTriangleRow(lastRow: List[Int], rowNumber: Int): List[Int] = {
      rowNumber match {
        case x if x > r => lastRow
        case 0 => findTriangleRow(List(1), rowNumber + 1)
        case _ => findTriangleRow(1 +: innerTriangleRow(List(), lastRow) :+ 1, rowNumber + 1)
      }
    }

    def getTriangleRow(): List[Int] = {
      findTriangleRow(List(), 0)
    }

    getTriangleRow()(c)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def isBalanced(parenthesesCount: Int) = {
      parenthesesCount == 0;
    }

    @tailrec
    def trackParentheses(parenthesesCount: Int, chars: List[Char]): Boolean = {
      if (chars.isEmpty) isBalanced(parenthesesCount)
      else
        chars.head match {
          case '(' => trackParentheses(parenthesesCount + 1, chars.tail)
          case ')' => if (isBalanced(parenthesesCount)) false else trackParentheses(parenthesesCount - 1, chars.tail)
          case _ => trackParentheses(parenthesesCount, chars.tail)
        }
    }

    trackParentheses(0, chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    @tailrec
    def changeTotals(currentTotals: List[Int], coins: List[Int]): List[Int] = {
      if (coins.isEmpty) currentTotals
      else {
        val coinValue = coins.head
        val maxNoOfThisDenomination = money / coinValue
        val newTotals = for (numberOfCoinsOfThisDenomination <- (0 to maxNoOfThisDenomination); aTotal <- currentTotals;
                             if (aTotal + (coinValue * numberOfCoinsOfThisDenomination)) <= money)
        yield aTotal + (coinValue * numberOfCoinsOfThisDenomination)
        changeTotals(newTotals.toList, coins.tail)
      }
    }

    if (money == 0 || coins.isEmpty) 0
    else {
      val totals = changeTotals(List(0), coins)
      totals.filter(total => total == money).size
    }
  }
}
