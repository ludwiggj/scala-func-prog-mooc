package week5

object listFun {
 def last[T](xs: List[T]): T = xs match {
   case List() => throw new Error("last of an empty list")
   case List(x) => x
   case y :: ys => last(ys)
 }                                                //> last: [T](xs: List[T])T
 
 last(List(1))                                    //> res0: Int = 1
 last(List("a", "b", "c"))                        //> res1: String = c
 
 def init[T](xs: List[T]): List[T] = xs match {
   case List() => throw new Error("init of an empty list")
   case List(x) => List()
   case y :: ys => y :: init(ys)
 }                                                //> init: [T](xs: List[T])List[T]
 
 init(List(1))                                    //> res2: List[Int] = List()
 init(List("a", "b", "c"))                        //> res3: List[String] = List(a, b)
 
 def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
   case List() => ys
   case z :: zs => z :: concat(zs, ys)
 }                                                //> concat: [T](xs: List[T], ys: List[T])List[T]
 
 concat(List(), List())                           //> res4: List[Nothing] = List()
 concat(List(1), List())                          //> res5: List[Int] = List(1)
 concat(List(), List(2))                          //> res6: List[Int] = List(2)
 concat(List(3, 4), List(5, 6, 7))                //> res7: List[Int] = List(3, 4, 5, 6, 7)
 
 def reverse[T](xs: List[T]): List[T] = xs match {
   case List() => xs
   case y :: ys => reverse(ys) ++ List(y)
 }                                                //> reverse: [T](xs: List[T])List[T]
 
 reverse(List())                                  //> res8: List[Nothing] = List()
 reverse(List(1))                                 //> res9: List[Int] = List(1)
 reverse(List(1,2,3))                             //> res10: List[Int] = List(3, 2, 1)
 
 def removeAt[T](n: Int, xs: List[T]) = {
   def removeIt(pos: Int, ys: List[T], acc: List[T]): List[T] =
     ys match {
       case List() => acc.reverse
       case z :: zs => if (pos == n) removeIt(pos+1, zs, acc)
                       else removeIt(pos+1, zs, z :: acc)
     }
     removeIt(0, xs, List())
 }                                                //> removeAt: [T](n: Int, xs: List[T])List[T]

 def removeAt2[T](n: Int, xs: List[T]) = {
   // xs.take(n) ++ xs.drop(n+1)
   (xs take n) ::: (xs drop n+1)
 }                                                //> removeAt2: [T](n: Int, xs: List[T])List[T]
 removeAt(-1, List('a', 'b', 'c', 'd'))           //> res11: List[Char] = List(a, b, c, d)
 removeAt(0, List('a', 'b', 'c', 'd'))            //> res12: List[Char] = List(b, c, d)
 removeAt(1, List('a', 'b', 'c', 'd'))            //> res13: List[Char] = List(a, c, d)
 removeAt(2, List('a', 'b', 'c', 'd'))            //> res14: List[Char] = List(a, b, d)
 removeAt(3, List('a', 'b', 'c', 'd'))            //> res15: List[Char] = List(a, b, c)
 removeAt(4, List('a', 'b', 'c', 'd'))            //> res16: List[Char] = List(a, b, c, d)
 removeAt(5, List('a', 'b', 'c', 'd'))            //> res17: List[Char] = List(a, b, c, d)
 removeAt(6, List('a', 'b', 'c', 'd'))            //> res18: List[Char] = List(a, b, c, d)
 
 removeAt2(-1, List('a', 'b', 'c', 'd'))          //> res19: List[Char] = List(a, b, c, d)
 removeAt2(0, List('a', 'b', 'c', 'd'))           //> res20: List[Char] = List(b, c, d)
 removeAt2(1, List('a', 'b', 'c', 'd'))           //> res21: List[Char] = List(a, c, d)
 removeAt2(2, List('a', 'b', 'c', 'd'))           //> res22: List[Char] = List(a, b, d)
 removeAt2(3, List('a', 'b', 'c', 'd'))           //> res23: List[Char] = List(a, b, c)
 removeAt2(4, List('a', 'b', 'c', 'd'))           //> res24: List[Char] = List(a, b, c, d)
 removeAt2(5, List('a', 'b', 'c', 'd'))           //> res25: List[Char] = List(a, b, c, d)
 removeAt2(6, List('a', 'b', 'c', 'd'))           //> res26: List[Char] = List(a, b, c, d)
}