package week7

object water_pourer_test {
  val problem = new Pouring(Vector(4, 9, 19))     //> problem  : week7.Pouring = week7.Pouring@4c5e176f
  problem.moves                                   //> res0: Seq[week7.water_pourer_test.problem.Move] = Vector(Empty(0), Empty(1),
                                                  //|  Empty(2), Fill(0), Fill(1), Fill(2), Pour(0,1), Pour(0,2), Pour(1,0), Pour(
                                                  //| 1,2), Pour(2,0), Pour(2,1))
  //problem.pathSets.take(3).toList(2)
  
  val x = problem.solutions(17)                   //> x  : Stream[week7.water_pourer_test.problem.Path] = Stream(Fill(0) Fill(1) P
                                                  //| our(0,2) Fill(0) Pour(0,2) Pour(1,2)---> Vector(0, 0, 17), ?)
  println(x.tail + " ============= " + x)         //> Stream(Fill(0) Pour(0,2) Fill(1) Pour(1,2) Fill(0) Pour(0,2)---> Vector(0, 0
                                                  //| , 17), ?) ============= Stream(Fill(0) Fill(1) Pour(0,2) Fill(0) Pour(0,2) P
                                                  //| our(1,2)---> Vector(0, 0, 17), Fill(0) Pour(0,2) Fill(1) Pour(1,2) Fill(0) P
                                                  //| our(0,2)---> Vector(0, 0, 17), ?)
  println(x.tail.tail + " ============= " + x)    //> Stream(Fill(0) Fill(1) Pour(0,2) Fill(0) Pour(1,2) Pour(0,2)---> Vector(0, 0
                                                  //| , 17), ?) ============= Stream(Fill(0) Fill(1) Pour(0,2) Fill(0) Pour(0,2) P
                                                  //| our(1,2)---> Vector(0, 0, 17), Fill(0) Pour(0,2) Fill(1) Pour(1,2) Fill(0) P
                                                  //| our(0,2)---> Vector(0, 0, 17), Fill(0) Fill(1) Pour(0,2) Fill(0) Pour(1,2) P
                                                  //| our(0,2)---> Vector(0, 0, 17), ?)
  
  println (x.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail)
                                                  //> Stream(Fill(0) Fill(1) Pour(1,2) Pour(0,2) Fill(0) Pour(0,2) Fill(1)---> Vec
                                                  //| tor(0, 9, 17), ?)
  val y = x take 3 toList                         //> y  : List[week7.water_pourer_test.problem.Path] = List(Fill(0) Fill(1) Pour(
                                                  //| 0,2) Fill(0) Pour(0,2) Pour(1,2)---> Vector(0, 0, 17), Fill(0) Pour(0,2) Fil
                                                  //| l(1) Pour(1,2) Fill(0) Pour(0,2)---> Vector(0, 0, 17), Fill(0) Fill(1) Pour(
                                                  //| 0,2) Fill(0) Pour(1,2) Pour(0,2)---> Vector(0, 0, 17))
  val insoluble = new Pouring(Vector(1,1))        //> insoluble  : week7.Pouring = week7.Pouring@4406cef4
  val z = insoluble.solutions(2)                  //> z  : Stream[week7.water_pourer_test.insoluble.Path] = Stream()
  
  
}