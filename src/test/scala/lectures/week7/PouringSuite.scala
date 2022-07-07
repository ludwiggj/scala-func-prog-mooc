package lectures.week7

import lectures.week7.water_pouring.Pouring
import lectures.week7.water_pouring.State

import org.scalatest.funsuite.AnyFunSuite

class PouringSuite extends AnyFunSuite {

  trait TestData {
    val exampleState: State = Vector(2, 7)

    val problem = new Pouring(Vector(4, 9))

    val empty_0 = new problem.Empty(0)
    val empty_1 = new problem.Empty(1)

    val fill_0 = new problem.Fill(0)
    val fill_1 = new problem.Fill(1)

    val pour_0_1 = new problem.Pour(0, 1)
    val pour_1_0 = new problem.Pour(1, 0)

    val initialState = Vector(0, 0)
    val initialPath = new problem.Path(Nil, initialState)

    lazy val pathStream = problem.from(Set(initialPath), Set(initialState))
  }

  test("initial state set correctly") {
    new TestData {
      assert(problem.initialState === Vector(0, 0))
    }
  }

  test("emptying all glasses sets their capacities to zero") {
    new TestData {
      assert(empty_0.change(exampleState) === Vector(0, 7))
      assert(empty_1.change(exampleState) === Vector(2, 0))
    }
  }

  test("filling all glasses sets their capacities to max") {
    new TestData {
      assert(fill_0.change(exampleState) === Vector(4, 7))
      assert(fill_1.change(exampleState) === Vector(2, 9))
    }
  }

  test("empty a glass by pouring contents into another glass") {
    new TestData {
      assert(pour_0_1.change(exampleState) === Vector(0, 9))
    }
  }

  test("partially empty a glass by pouring contents into another glass") {
    new TestData {
      assert(pour_1_0.change(exampleState) === Vector(4, 5))
    }
  }

  test("all available moves for two glasses") {
    new TestData {
      val expectedMoves = Set(
        empty_0, empty_1, fill_0, fill_1, pour_0_1, pour_1_0
      )
      assert(problem.moves.toSet -- expectedMoves === Set.empty)
    }
  }

  test("path tracks single move correctly") {
    new TestData {
      val expectedPath = new problem.Path(
        List(fill_0), Vector(4, 0)
      )
      assert(initialPath.extend(fill_0) === expectedPath)
    }
  }

  test("path tracks several moves correctly") {
    new TestData {
      val expectedPath = new problem.Path(
        List(fill_0, pour_0_1, fill_0), Vector(4, 4)
      )
      assert(initialPath.extend(fill_0).extend(pour_0_1).extend(fill_0) === expectedPath)
    }
  }

  test("from has correct initial state") {
    new TestData {
      val beforeFirstMove = pathStream.head
      assert(beforeFirstMove -- Set(initialPath) === Set.empty)
    }
  }

  test("from state after first move") {
    new TestData {
      val expectedPaths = Set(
        new problem.Path(
          List(fill_0), Vector(4, 0)
        ),
        new problem.Path(
          List(fill_1), Vector(0, 9)
        )
      )

      val pathsAfterFirstMove = pathStream.tail.head
      assert(pathsAfterFirstMove -- expectedPaths === Set.empty)
    }
  }

  test("from state after second move") {
    new TestData {
      val expectedPaths = Set(
        new problem.Path(
          List(fill_1, fill_0), Vector(4, 9)
        ),
        new problem.Path(
          List(pour_0_1, fill_0), Vector(0, 4)
        ),
        new problem.Path(
          List(fill_0, fill_1), Vector(4, 9)
        ),
        new problem.Path(
          List(pour_1_0, fill_1), Vector(4, 5)
        )
      )

      val pathsAfterSecondMove = pathStream.drop(2).head
      assert(pathsAfterSecondMove -- expectedPaths === Set.empty)
    }
  }

  test("from state after third move") {
    new TestData {
      val expectedPaths = Set(
        new problem.Path(
          List(fill_0, pour_0_1, fill_0), Vector(4, 4)
        ),
        new problem.Path(
          List(empty_0, pour_1_0, fill_1), Vector(0, 5)
        )
      )
      val pathsAfterThirdMove = pathStream.drop(3).head
      assert(pathsAfterThirdMove -- expectedPaths === Set.empty)
    }
  }

  test("from state after fourth move") {
    new TestData {
      val expectedPaths = Set(
        new problem.Path(
          List(pour_0_1, fill_0, pour_0_1, fill_0), Vector(0, 8)
        ),
        new problem.Path(
          List(pour_1_0, empty_0, pour_1_0, fill_1), Vector(4, 1)
        )
      )
      val pathsAfterFourthMove = pathStream.drop(4).head
      assert(pathsAfterFourthMove -- expectedPaths === Set.empty)
    }
  }

  test("can find first solution that gives capacity of 8") {
    new TestData {
      val firstPathToGiveCapacityOf8 = new problem.Path(
        List(pour_0_1, fill_0, pour_0_1, fill_0), Vector(0, 8)
      )
      assert(problem.solutions(8).head === firstPathToGiveCapacityOf8)
    }
  }

  test("can find first solution that gives capacity of 1") {
    new TestData {
      val firstPathToGiveCapacityOf8 = new problem.Path(
        List(pour_1_0, empty_0, pour_1_0, fill_1), Vector(4, 1)
      )
      assert(problem.solutions(1).head === firstPathToGiveCapacityOf8)
    }
  }

  test("cannot find solution that gives capacity of 10") {
    new TestData {
      assert(problem.solutions(10) === Stream())
    }
  }

  test("multiple solutions that give a capacity of 4") {
      new TestData {
        println(problem.solutions(4).head)
        println(problem.solutions(4).drop(1).head)
        println(problem.solutions(4).drop(2).head)
        println(problem.solutions(4).drop(3).head)
        println(problem.solutions(4).drop(4).head)
        println(problem.solutions(4).drop(5).head)
        println(problem.solutions(4).drop(6).head)
        println(problem.solutions(4).drop(7).head)
        println(problem.solutions(4).drop(8).head)
        println(problem.solutions(4).drop(9).head)
        println(problem.solutions(4).drop(10).head)
        println(problem.solutions(4).drop(11).head)
      }
    }
}