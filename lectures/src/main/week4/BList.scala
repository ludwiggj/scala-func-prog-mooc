package week4

object BList {
   def List() = new Nil
   def List(a: Int) = new Cons(a, new Nil)
   def List(a: Int, b: Int) = new Cons(a, new Cons(b, new Nil))
   
   def main(args: Array[String]) {     
     println("Hello, world!")
     println(BList.List)
     println(BList.List(1))
     println(BList.List(1,2))
   }
}