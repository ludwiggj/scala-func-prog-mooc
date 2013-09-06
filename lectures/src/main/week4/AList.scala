package week4

object AList {
   def apply[T]() = new Nil
   def apply[T](x1: T): List[T] =  new Cons(x1, new Nil);
   def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
   def apply[T](x1: T, x2: T, x3: T): String = "Hey! This object was passed arguments [" + x1 + "] [" + x2 + "] and [" + x3 + "]!"
   
   def main(args: Array[String]) {     
     println("Hello, world!")
     println(AList())
     println(AList(1))
     println(AList(2, 3))
     println(AList(4, 5, 6))
   }
}