package week7
   
object bloxorz {


  val level =
        """ST
          |oo
          |oo"""                                  //> level  : String = ST
                                                  //|           |oo
                                                  //|           |oo
          
  level.split("\n")                               //> res0: Array[String] = Array(ST, "          |oo", "          |oo")
          
  Array("ST", "oo", "oo") map(str => Vector(str: _*))
                                                  //> res1: Array[scala.collection.immutable.Vector[Char]] = Array(Vector(S, T), V
                                                  //| ector(o, o), Vector(o, o))
                                                  
    
    
  val words = "The quick brown fox jumped over the lazy dog".split(' ')
                                                  //> words  : Array[String] = Array(The, quick, brown, fox, jumped, over, the, la
                                                  //| zy, dog)
// this works because scala.Ordering will implicitly provide an Ordering[Tuple2[Int, Char]]
words.sortBy(_.length)                            //> res2: Array[String] = Array(The, fox, the, dog, over, lazy, quick, brown, ju
                                                  //| mped)
                                                  
  val x = List(List(), List(1),List(1,2,3, 4), List(1,2,3))
                                                  //> x  : List[List[Int]] = List(List(), List(1), List(1, 2, 3, 4), List(1, 2, 3)
                                                  //| )
  
  val y = x map (_ length)                        //> y  : List[Int] = List(0, 1, 4, 3)
  
  y.sortWith(_ < _)                               //> res3: List[Int] = List(0, 1, 3, 4)
  y.sortWith((x, y) => x < y) == y                //> res4: Boolean = false
  y.sortWith(_ < _) == y                          //> res5: Boolean = false
  

                                                  
  /*
   * Take 1
   *
   *def neighbors: List[(Block, Move)]
   *
   * def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] = {
    val nextMoves: Seq[(Block, List[Move])] = for {
      (neighborBlock, move) <- (b neighbors)
      if (neighborBlock isLegal)
    } yield (neighborBlock, move :: history)
    (nextMoves.toStream) #:: (nextMoves map (tuple => neighborsWithHistory(tuple._1, tuple._2)))
  }
  
  Take 2
  
   def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] = {
    val nextMoves: Seq[(Block, List[Move])] = for {
      (neighborBlock, move) <- (b neighbors)
      if (neighborBlock isLegal)
    } yield (neighborBlock, move :: history)
    
    val s: Stream[(Block, List[Move])] = Stream()
    
    s ++ s
    
    val nextIteration:Stream[(Block, List[Move])] = (nextMoves map (tuple => neighborsWithHistory(tuple._1, tuple._2))).reduceLeft(_ ++ _)
    
    //nextMoves.foldRight(nextIteration)(_ #:: _)
    s

    //
    //val z = ((nextMoves) #:: ))
  }
  
  
  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extend // calls path.extend on each move to generate a new path
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }
  
  
  
  Take 3
  
  
   def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] = {
    
    //(b: Block, history: List[Move])
    
    def foldr[A, B]( combine: (A, =>B) => B, base: B )(xs: Stream[A]): B = if (xs.isEmpty) base else combine(xs.head, foldr(combine, base)(xs.tail))
    
    def neighborsWithHistoryFromBlockList(paths: List[(Block, List[Move])]): Stream[(Block, List[Move])] = {
      println("Iterate, paths: [" + paths.length + "] " + paths)
      if (paths.isEmpty) Stream.empty else {
      val nextMoves: Seq[(Block, List[Move])] = for {
        (currentBlock, history) <- paths
        (neighborBlock, move) <- (currentBlock neighbors)
        if (neighborBlock isLegal)
      } yield (neighborBlock, move :: history)
    
    val s: Stream[(Block, List[Move])] = Stream()
    
    //s ++ s
    
    //val nextIteration:Stream[(Block, List[Move])] = (nextMoves map (tuple => neighborsWithHistory(tuple._1, tuple._2))).reduceLeft(_ ++ _)
    
    //nextMoves.foldr(neighborsWithHistoryFromBlockList(nextMoves.toList))(_ #:: _)
    
    //def combine(x: (Block, List[Move]), y: => Stream[(Block, List[Move])]) = x #:: y
    //foldr(combine, neighborsWithHistoryFromBlockList(nextMoves.toList))(nextMoves.toStream)
    
    (nextMoves.toList(0)) #:: neighborsWithHistoryFromBlockList(nextMoves.toList)
    
    //foldr(combine, 0)(xs)
    
   //  def filter[A](p:A=>Boolean, xs: Stream[A]) = {
  //16    def combine(x: A, xs: =>Stream[A]) = if (p(x)) x #:: xs else xs
  //17    foldr(combine , Stream.empty)(xs)
    
    
    //y ++ neighborsWithHistoryFromBlockList(nextMoves.toList)
    //s
    }
    }
    neighborsWithHistoryFromBlockList(List((b, history)))
    
    //
    //val z = ((nextMoves) #:: ))
  }
  
  LAST TAKE
  
  def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] = {
    val nextMoves: Seq[(Block, List[Move])] = for {
      (neighborBlock, move) <- (b neighbors)
      if (neighborBlock isLegal)
    } yield (neighborBlock, move :: history)
    nextMoves.toStream
    
    nextMoves match {
      case _ => _ #::
      case Nil =>
    }
    
    
    //val nextIteration:Stream[(Block, List[Move])] = (nextMoves map (tuple => neighborsWithHistory(tuple._1, tuple._2))).reduceLeft(_ ++ _)
    
    //
    lazy val nextIteration:Stream[(Block, List[Move])] = (nextMoves map (tuple => neighborsWithHistory(tuple._1, tuple._2))).reduceLeft(_ ++ _)
    //
    nextMoves.foldRight(nextIteration)(_ #:: _)
    //
    //val z = ((nextMoves) #:: ))
  }
  
  
  //initial.head #:: from(more, explored ++ (more map (_._1)))
        //initial.foldRight(from(more, explored ++ (more map (_._1))))(_ #:: _)
        
  */
  
                                                  
}