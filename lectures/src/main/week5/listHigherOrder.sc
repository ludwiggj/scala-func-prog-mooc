package week5

object listHigherOrder {
  val data = List("a", "a", "a", "b", "c", "c", "a")
                                                  //> data  : List[String] = List(a, a, a, b, c, c, a)


  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 => List(x :: xs1 takeWhile(y => y == x)) ++ pack(xs1 dropWhile(y => y == x))
  }                                               //> pack: [T](xs: List[T])List[List[T]]
  
  def packNicer[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, second) = xs span (y => y == x)
      first :: packNicer(second)
  }                                               //> packNicer: [T](xs: List[T])List[List[T]]
  
  val x = List(List(1 :: List(2)),List(2))        //> x  : List[List[Any]] = List(List(List(1, 2)), List(2))
  val y = List(List(1)) ++ List(List(2))          //> y  : List[List[Int]] = List(List(1), List(2))
  val z = pack(data)                              //> z  : List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
  val zz = packNicer(data)                        //> zz  : List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
  def encode[T](xs: List[T]): List[(T, Int)] = {
    packNicer(xs) map (x => (x head, x length))
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]
  
  encode(data)                                    //> res0: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
}