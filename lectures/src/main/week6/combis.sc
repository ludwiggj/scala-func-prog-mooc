package week6

object combis {
  (1 to 10) flatMap (x => (1 to 5) map (y => (x, y)))
                                                  //> res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (1,2)
                                                  //| , (1,3), (1,4), (1,5), (2,1), (2,2), (2,3), (2,4), (2,5), (3,1), (3,2), (3,3)
                                                  //| , (3,4), (3,5), (4,1), (4,2), (4,3), (4,4), (4,5), (5,1), (5,2), (5,3), (5,4)
                                                  //| , (5,5), (6,1), (6,2), (6,3), (6,4), (6,5), (7,1), (7,2), (7,3), (7,4), (7,5)
                                                  //| , (8,1), (8,2), (8,3), (8,4), (8,5), (9,1), (9,2), (9,3), (9,4), (9,5), (10,1
                                                  //| ), (10,2), (10,3), (10,4), (10,5))
                                                  
   def scalarProduct(xs: Vector[Int], ys: Vector[Int]): Int = {
     //(xs zip ys).map(xy => xy._1 * xy._2).sum
     (xs zip ys).map {case (x, y) => x * y}.sum
   }                                              //> scalarProduct: (xs: Vector[Int], ys: Vector[Int])Int
   
   
   def scalarProduct1(xs: Vector[Int], ys: Vector[Int]): Int = {
     (for {
       pos <- 0 until xs.size
     } yield (xs(pos) * ys(pos))).sum
   }                                              //> scalarProduct1: (xs: Vector[Int], ys: Vector[Int])Int
   
   def scalarProduct2(xs: Vector[Int], ys: Vector[Int]): Int = {
     (for {
       (x, y) <- (xs zip ys)
     } yield (x * y)).sum
   }                                              //> scalarProduct2: (xs: Vector[Int], ys: Vector[Int])Int
   
   Vector(178,2,3)(0)                             //> res1: Int = 178
     
   scalarProduct(Vector(1,2,3), Vector(4,5,6))    //> res2: Int = 32
   scalarProduct1(Vector(1,2,3), Vector(4,5,6))   //> res3: Int = 32
   scalarProduct2(Vector(1,2,3), Vector(4,5,6))   //> res4: Int = 32
   
   def isPrime(n:Int):Boolean = {
     (2 until n) forall (x => n % x != 0)
   }                                              //> isPrime: (n: Int)Boolean
   
   isPrime(11)                                    //> res5: Boolean = true
   
   def primePairs(n: Int) = {
     ((1 until n) map (i => (1 until i) map (j => (i, j)))).flatten
   }                                              //> primePairs: (n: Int)scala.collection.immutable.IndexedSeq[(Int, Int)]
   
   def primePairs2(n: Int) = {
     ((1 until n) flatMap (i => (1 until i) map (j => (i, j)))) filter {case (x,y) => isPrime(x + y)}
   }                                              //> primePairs2: (n: Int)scala.collection.immutable.IndexedSeq[(Int, Int)]
   
   def primePairs3(n: Int) = {
     for {
       i <- 1 until n
       j <- 1 until i
       if isPrime(i + j)
     } yield (i,j)
   }                                              //> primePairs3: (n: Int)scala.collection.immutable.IndexedSeq[(Int, Int)]
   
   primePairs(5)                                  //> res6: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 1), (3,2), (4,1), (4,2), (4,3))
   
   primePairs2(7)                                 //> res7: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5))
   primePairs3(7)                                 //> res8: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5))
   for {
     i <- List(10,20,30,40,50)
     j <- 1 to 3
   } yield i                                      //> res9: List[Int] = List(10, 10, 10, 20, 20, 20, 30, 30, 30, 40, 40, 40, 50, 
                                                  //| 50, 50)
   
   def queens(n: Int): Set[List[Int]] = {
     def placeQueens(k: Int): Set[List[Int]] =
       if (k == 0) Set(List())
       else
         for {
           queens <- placeQueens(k-1)
           col <- 0 until n
           if isSafe(col, queens)
         } yield col :: queens
     placeQueens(n)
   }                                              //> queens: (n: Int)Set[List[Int]]
   
   def sameDiag(queens: Seq[(Int, Int)], proposed: (Int,Int), f: ((Int,Int)) => Int): Boolean = {
     val diagVal:Int = f(proposed)
     queens map (f(_)) exists (_ == diagVal)
   }                                              //> sameDiag: (queens: Seq[(Int, Int)], proposed: (Int, Int), f: ((Int, Int)) =
                                                  //| > Int)Boolean
   
   def isSafe(col: Int, queens: List[Int]): Boolean = {
     def pairSum = (p: (Int, Int)) => p._1 + p._2
     def pairDiff = (p: (Int, Int)) => p._1 - p._2
     
     val currentQueens = for {
       row <- 0 until queens.size
     } yield (row, queens(queens.size - row - 1))
     
     val proposed = (queens.size, col)
     
     !queens.contains(col) && !sameDiag(currentQueens, proposed, pairSum) && !sameDiag(currentQueens, proposed, pairDiff)
   }                                              //> isSafe: (col: Int, queens: List[Int])Boolean
   
   def isSafe2(col: Int, queens: List[Int]): Boolean = {
     val row = queens.length
     val queensWithRow = (row - 1 to 0 by -1) zip queens
     queensWithRow forall {
       case (r,c) => col != c && math.abs(col-c) != row - r
     }
   }                                              //> isSafe2: (col: Int, queens: List[Int])Boolean
   
   def show(queens: List[Int]) = {
     val lines =
       for (col <- queens.reverse)
       yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
       "\n" + (lines mkString "\n")
   }                                              //> show: (queens: List[Int])String
   
   (queens(5) map show) mkString "\n"             //> res10: String = "
                                                  //| * * * X * 
                                                  //| * X * * * 
                                                  //| * * * * X 
                                                  //| * * X * * 
                                                  //| X * * * * 
                                                  //| 
                                                  //| * * X * * 
                                                  //| * * * * X 
                                                  //| * X * * * 
                                                  //| * * * X * 
                                                  //| X * * * * 
                                                  //| 
                                                  //| * * * * X 
                                                  //| * * X * * 
                                                  //| X * * * * 
                                                  //| * * * X * 
                                                  //| * X * * * 
                                                  //| 
                                                  //| X * * * * 
                                                  //| * * * X * 
                                                  //| * X * * * 
                                                  //| * * * * X 
                                                  //| * * X * * 
                                                  //| 
                                                  //| X * * * * 
                                                  //| * * X * * 
                                                  //| * * * * X 
                                                  //| * X * * * 
                                                  //| * * * X * 
                                                  //| 
                                                  //| * * * * X 
                                                  //| * X * * * 
                                                  //| * * * X * 
                                                  //| X * * * * 
                                                  //| * * X * * 
                                                  //| 
                                                  //| * * * X * 
                                                  //| X * * * * 
                                                  //| * * X * * 
                                                  //| * * * * X 
                                                  //| * X * * * 
                                                  //| 
                                                  //| * * X * * 
                                                  //| X * * * * 
                                                  //| * * * X * 
                                                  //| * X * * * 
                                                  //| * * * * X 
                                                  //| 
                                                  //| * X * * * 
                                                  //| * * * X * 
                                                  //| X * * * * 
                                                  //| * * X * * 
                                                  //| * * * * X 
                                                  //| 
                                                  //| * X * * * 
                                                  //| * * * * X 
                                                  //| * * X * * 
                                                  //| X * * * * 
                                                  //| * * * X * "
}