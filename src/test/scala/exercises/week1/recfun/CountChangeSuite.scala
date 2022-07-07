package exercises.week1.recfun

import org.scalatest.funsuite.AnyFunSuite

class CountChangeSuite extends AnyFunSuite {
  import Main.countChange
  test("countChange: example given in instructions") {
    assert(countChange(4,List(1,2)) === 3)
  }

  test("countChange: sorted CHF") {
    assert(countChange(300,List(5,10,20,50,100,200,500)) === 1022)
  }

  test("countChange: no pennies") {
    assert(countChange(301,List(5,10,20,50,100,200,500)) === 0)
  }

  test("countChange: unsorted CHF") {
    assert(countChange(300,List(500,5,50,100,20,200,10)) === 1022)
  }
  
  test("countChange: 0 ways of making 0 total") {
    assert(countChange(0,List(500,5,50,100,20,200,10)) === 0)
  }
  
  test("countChange: 0 ways of making total with no coins") {
    assert(countChange(300,List()) === 0)
  }
  
  test("countChange: single coin") {
    assert(countChange(2,List(1)) === 1)
  }
  
  test("countChange: single coin which is too large") {
    assert(countChange(2,List(3)) === 0)
  }
}