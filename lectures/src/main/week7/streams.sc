package week7

object streams {
  def listRange(lo: Int, hi: Int): List[Int] =
    if (lo >= hi) Nil
    else lo :: listRange(lo+1, hi)                //> listRange: (lo: Int, hi: Int)List[Int]

  def streamRange(lo: Int, hi: Int): Stream[Int] =
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo+1, hi))   //> streamRange: (lo: Int, hi: Int)Stream[Int]
    
  def printStreamRange(lo: Int, hi: Int): Stream[Int] = {
    println("Lo [" + lo + "]")
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo+1, hi))
  }                                               //> printStreamRange: (lo: Int, hi: Int)Stream[Int]
    
  def isPrime(n:Int):Boolean = {
     (2 until n) forall (x => n % x != 0)
  }                                               //> isPrime: (n: Int)Boolean
  
  
      
  listRange(1,1)                                  //> res0: List[Int] = List()
  listRange(1,5)                                  //> res1: List[Int] = List(1, 2, 3, 4)
  
  streamRange(1,1)                                //> res2: Stream[Int] = Stream()
  streamRange(1,5)                                //> res3: Stream[Int] = Stream(1, ?)
  streamRange(1,5).tail                           //> res4: scala.collection.immutable.Stream[Int] = Stream(2, ?)
  
  val x = streamRange(1,10)                       //> x  : Stream[Int] = Stream(1, ?)
  println(x)                                      //> Stream(1, ?)
  val evens = (x filter (_ % 2 == 0))             //> evens  : scala.collection.immutable.Stream[Int] = Stream(2, ?)
  //println(evens.tail + " " + evens.toList)
  println(evens.tail + " " + evens)               //> Stream(4, ?) Stream(2, 4, ?)
  println(evens.tail.tail + " " + evens)          //> Stream(6, ?) Stream(2, 4, 6, ?)
  println(evens.tail.tail.tail + " " + evens)     //> Stream(8, ?) Stream(2, 4, 6, 8, ?)
  println(evens.tail.tail.tail.tail + " " + evens)//> Stream() Stream(2, 4, 6, 8)
  
  val aStream = (1000 to 10000).toStream          //> aStream  : scala.collection.immutable.Stream[Int] = Stream(1000, ?)
  (aStream filter isPrime)(12)                    //> res5: Int = 1087
  println(aStream)                                //> Stream(1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 10
                                                  //| 11, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023,
                                                  //|  1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1033, 1034, 1035, 10
                                                  //| 36, 1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048,
                                                  //|  1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 10
                                                  //| 61, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073,
                                                  //|  1074, 1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085, 10
                                                  //| 86, 1087, ?)
  val z = printStreamRange(1, 10)                 //> Lo [1]
                                                  //| z  : Stream[Int] = Stream(1, ?)
  z.take(3).toList                                //> res6: List[Int] = List(1, 2, 3)
  println(z)                                      //> Stream(1, 2, 3, ?)
  z.take(6)                                       //> res7: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  println(z)                                      //> Stream(1, 2, 3, ?)
  z.take(6).toList                                //> res8: List[Int] = List(1, 2, 3, 4, 5, 6)
  println(z)                                      //> Stream(1, 2, 3, 4, 5, 6, ?)
}