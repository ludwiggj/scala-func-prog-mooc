package week5

object folds {
  def sum(xs: List[Int]) = (0 :: xs) reduceLeft ((x,y) => x + y)
  def sum1(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _)
  def sum2(xs: List[Int]) = (xs foldLeft 0) (_ + _)
  def product(xs: List[Int]) = (1 :: xs) reduceLeft ((x,y) => x * y)
  def product1(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _)
  def product2(xs: List[Int]) = (xs foldLeft 1)(_ * _)
  
  def reduceLeft[T](list:List[T])(op: (T, T) => T): T = list match {
    case Nil => throw new Error("Nil.reduceLeft")
    case x :: xs => foldLeft (x)(xs)(op)
  }
  
  def foldLeft[T, U](z: U)(list:List[T])(op:(U, T) => U): U = list match {
    case Nil => z
    case x :: xs => foldLeft(op(z,x)) (xs) (op)
  }
  
  def reduceRight[T](list:List[T])(op: (T, T) => T): T = list match {
    case Nil => throw new Error("Nil.reduceLeft")
    case x :: Nil => x
    case x :: xs => op(x, reduceRight(xs)(op))
  }
  
  def foldRight[T, U](z: U)(list:List[T])(op:(T, U) => U): U = list match {
    case Nil => z
    case x :: xs => op(x, foldRight(z)(xs)(op))
  }
  
  def concat[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys) (_ :: _)
    
//  def concat2[T](xs: List[T], ys: List[T]): List[T] =
//    (xs foldLeft ys) (_ :: _) // Does not work!
  
  def main(args: Array[String]) = {
    println(sum(List(2,4,8)))
    println(sum1(List(2,4,8)))
    println(sum2(List(2,4,8)))
    
    println(product(List(2,4,8)))
    println(product1(List(2,4,8)))
    println(product2(List(2,4,8)))
    
    println(reduceLeft(List(2,4,8))(_ + _))
    println(foldLeft(1)(List(2,4,8))(_ * _))
    
    println(reduceRight(List(20,40,80))(_ + _))
    println(foldRight(2)(List(2,4,8))(_ * _))
    
    println(concat(List(1,2,3), List(4,5,6)))
  }
}