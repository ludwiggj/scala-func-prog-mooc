package exercises.week7.streams

import org.scalatest.funsuite.AnyFunSuite

import scala.language.postfixOps

class BloxorzSuite extends AnyFunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }                           
          
  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }
    
  trait exampleLevelVector extends StringParserTerrain  {
    val levelVector: Vector[Vector[Char]] = Vector(Vector('S', 'T', '-'), Vector('o', 'o', 'o'), Vector('o', 'o', 'o'))
    val terrFn = terrainFunction(levelVector);
    val level = "dummy"
  }
  
  test("terrain function returns true for start position") {
    new exampleLevelVector {
      assert(terrFn(Pos(0,0)), "0,0")
    }
  }
  
  test("terrain function returns true for end position") {
    new exampleLevelVector {
      assert(terrFn(Pos(0,1)), "0,1")
    }
  }
  
  test("terrain function returns true for valid grid position") {
    new exampleLevelVector {
      assert(terrFn(Pos(2,2)), "2,2")
    }
  }
  
  test("terrain function returns false for invalid grid position") {
    new exampleLevelVector {
      assert(!terrFn(Pos(0,2)), "0,2")
    }
  }
  
  test("terrain function returns false if x out of bounds") {
    new exampleLevelVector {
      assert(!terrFn(Pos(3,0)), "3,0")
    }
  }
  
  test("terrain function returns false if y out of bounds") {
    new exampleLevelVector {
      assert(!terrFn(Pos(0,5)), "0,5")
    }
  }
  
  test("terrain function returns false if x is negative") {
    new exampleLevelVector {
      assert(!terrFn(Pos(-3,0)), "-3,0")
    }
  }
  
  test("terrain function returns false if y is negative") {
    new exampleLevelVector {
      assert(!terrFn(Pos(0,-5)), "0,-5")
    }
  }
 
  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(!terrain(Pos(4,11)), "4,11")
    }
  }

  test("findChar start level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }
  
  test("findChar end level 1") {
    new Level1 {
      assert(goal == Pos(4,7))
    }
  }
  
  test("move left from standing") {
    new Level1 {
      val block = new Block(new Pos(2,2), new Pos(2,2))
      val expectedBlock = new Block(new Pos(2,0), new Pos(2,1))
      assert((block left) == expectedBlock) 
    }
  }
  
  test("move left to standing") {
    new Level1 {
      val block = new Block(new Pos(2,2), new Pos(2,3))
      val expectedBlock = new Block(new Pos(2,1), new Pos(2,1))
      assert((block left) == expectedBlock)
    }
  }
  
  test("move left roll") {
    new Level1 {
      val block = new Block(new Pos(2,2), new Pos(3,2))
      val expectedBlock = new Block(new Pos(2,1), new Pos(3,1))
      assert((block left) == expectedBlock)
    }
  }
  
  test("block is standing") {
    new Level1 {
      assert(new Block(new Pos(1,1), new Pos(1,1)) isStanding)
    }
  }
  
  test("block is not standing") {
    new Level1 {
      assert(! ((new Block(new Pos(1,1), new Pos(2,1))) isStanding))
    }
  }
  
  test("legal block") {
    new Level1 {
      val block = new Block(new Pos(0,0), new Pos(0,1)) 
      assert(block isLegal)
    }
  }
  
  test("illegal block") {
    new Level1 {
      val block = new Block(new Pos(0,2), new Pos(0,3)) 
      assert(!(block isLegal))
    }
  }

  test("start block") {
    new Level1 {
      val expectedStartBlock = new Block(new Pos(1,1), new Pos(1,1)) 
      assert(startBlock == expectedStartBlock)
    }
  }

  test("start block neighbours") {
    new Level1 {
      val expectedNeighbours: List[(Block, Move)] =
        List(
            (new Block(new Pos(1,-1), new Pos(1,0)), Left),
            (new Block(new Pos(1,2), new Pos(1,3)), Right),
            (new Block(new Pos(-1,1), new Pos(0,1)), Up),
            (new Block(new Pos(2,1), new Pos(3,1)), Down)
        )
      
      assert(startBlock.neighbors == expectedNeighbours)
    }
  }
  
  test("start block legal neighbours") {
    new Level1 {
      val expectedNeighbours: List[(Block, Move)] =
        List(            
            (new Block(new Pos(1,2), new Pos(1,3)), Right),
            (new Block(new Pos(2,1), new Pos(3,1)), Down)
        )
      
      assert(startBlock.legalNeighbors == expectedNeighbours)
    }
  }
     

 test("block in done position") {
    new Level1 {      
      assert(done(new Block(new Pos(4,7), new Pos(4,7))))
    }
  }
 
  test("block in close but no cigar done position") {
    new Level1 {      
      assert(! done(new Block(new Pos(4,7), new Pos(4,8))))
    }
  }
  
  test("neighbours with history") {
    new Level1 {
      assert(neighborsWithHistory(Block(Pos(1,1),Pos(1,1)), List(Left,Up)).toSet ==
        Set(
            (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
            (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
            ))            
      }    
  }
  
  
  test("newNeighbours") {
    new Level1 {
      val newNeighbours: Stream[(Block, List[Move])] = Set(
          (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
          (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
          ).toStream
      val explored: Set[Block] = Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1)))
      val expected = Set((Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up)))
      assert(newNeighborsOnly(newNeighbours, explored).toSet == expected)
    }
  }
  
   test("from") {
    new Level1 {
      val initialState: Stream[(Block, List[Move])] = List((Block(Pos(0,0), Pos(0,0)), List[Move]())).toStream
      val theStream: Stream[(Block, List[Move])] = from(initialState, Set(Block(Pos(0,0), Pos(0,0))))
           
      val iteration1 = (theStream drop 1 take 2).toSet      
      assert(iteration1 === Set((Block(Pos(0,1),Pos(0,2)), List(Right)), (Block(Pos(1,0),Pos(2,0)), List(Down))))
      
      val iteration2 = (theStream drop 3 take 2).toSet      
      assert(iteration2 === Set((Block(Pos(1,1),Pos(1,2)), List(Down, Right)), (Block(Pos(1,1),Pos(2,1)), List(Right, Down))))
      
      val iteration3 = (theStream drop 5 take 6).toSet      
      assert(iteration3 === Set((Block(Pos(1,0),Pos(1,0)), List(Left, Down, Right)), (Block(Pos(1,3),Pos(1,3)), List(Right, Down, Right)), (Block(Pos(2,1),Pos(2,2)), List(Down, Down, Right)),
                                (Block(Pos(1,2),Pos(2,2)), List(Right, Right, Down)), (Block(Pos(0,1),Pos(0,1)), List(Up, Right, Down)), (Block(Pos(3,1),Pos(3,1)), List(Down, Right, Down))))
      }
  }
   
  test("from keeps answers in ascending order of path length") {
    new Level1 {
      val initialState: Stream[(Block, List[Move])] = List((Block(Pos(0,0), Pos(0,0)), List[Move]())).toStream
      val theStream: Stream[(Block, List[Move])] = from(initialState, Set(Block(Pos(0,0), Pos(0,0))))
      val first2000Results = ((theStream drop 1 take 2000).toList)
      val solutionLengths =  first2000Results map (_._2 length)
  
      assert(solutionLengths.sortWith(_ < _) == solutionLengths)
    }
  }
  
  test("pathsFromStart") {
   new Level1 {
     val firstTwoIterations: Set[(Block, List[Move])] = (pathsFromStart drop 1 take 5) toSet
     val expected = Set((Block(Pos(1,2),Pos(1,3)), List(Right)),
                        (Block(Pos(2,1),Pos(3,1)), List(Down)),
                        (Block(Pos(1,4),Pos(1,4)), List(Right, Right)),
                        (Block(Pos(2,2),Pos(2,3)), List(Down, Right)),
                        (Block(Pos(2,2),Pos(3,2)), List(Right, Down)))
     assert(firstTwoIterations === expected)
   }
  }
  
  trait SimpleLevel extends SolutionChecker {
    val level =
    """oooo-
      |oooo-
      |oSTo-""".stripMargin

    val optsolution = List(Down, Right, Up)
  }
  
  test("goal paths") {
    new SimpleLevel {
      print("Goal: " + goal)
      val solutions = pathsToGoal toSet
      val expected = Set((Block(Pos(2,2),Pos(2,2)), List(Down, Right, Up))
          //,
            //             (Block(Pos(2,2),Pos(2,2)), List(Down, Left, Left, Up, Right, Right, Down, Left, Up))
                        )
      assert(solutions === expected)
    }
  }
  
  test("goal paths 2") {
    new Level1 {
      println(pathsToGoal toSet)      
    }
  }
  
  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
    
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
      println(solution)
    }
  }
}