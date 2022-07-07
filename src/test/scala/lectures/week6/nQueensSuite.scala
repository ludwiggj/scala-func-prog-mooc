package lectures.week6

import org.scalatest.funsuite.AnyFunSuite
import lectures.week6.combinatorial.nQueens

class nQueensSuite extends AnyFunSuite {

  test("nQueens.isSafe.1") {
    assert(nQueens.isSafe(0, List()) === true)
  }

  test("nQueens.isSafe.2") {
    assert(nQueens.isSafe(0, List(2, 0)) === false)
  }

  test("nQueens.isSafe.3") {
    assert(nQueens.isSafe(1, List(2, 0)) === false)
  }

  test("nQueens.isSafe.4") {
    assert(nQueens.isSafe(2, List(2, 0)) === false)
  }

  test("nQueens.isSafe.5") {
    assert(nQueens.isSafe(3, List(2, 0)) === false)
  }

  test("nQueens.isSafe.6") {
    assert(nQueens.isSafe(4, List(2, 0)) === true)
  }

  test("nQueens.isSafe2.1") {
    assert(nQueens.isSafe2(0, List()) === true)
  }

  test("nQueens.isSafe2.2") {
    assert(nQueens.isSafe2(0, List(2, 0)) === false)
  }

  test("nQueens.isSafe2.3") {
    assert(nQueens.isSafe2(1, List(2, 0)) === false)
  }

  test("nQueens.isSafe2.4") {
    assert(nQueens.isSafe2(2, List(2, 0)) === false)
  }

  test("nQueens.isSafe2.5") {
    assert(nQueens.isSafe2(3, List(2, 0)) === false)
  }

  test("nQueens.isSafe2.6") {
    assert(nQueens.isSafe2(4, List(2, 0)) === true)
  }
}