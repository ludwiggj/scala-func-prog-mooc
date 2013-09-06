package week5

object sorter {
  def msort[T](xs: List[T], mergeLists: (List[T], List[T]) => List[T]): List[T] = {
    def sort(xs: List[T]): List[T] = {
      val n = xs.length / 2
      if (n == 0) xs
      else {
        val (first, second) = xs splitAt n
        mergeLists(sort(first), sort(second))
      }
    }
    sort(xs)
  }                                               

  def merge(xs: List[Int], ys: List[Int]): List[Int] =
        xs match {
          case Nil => ys
          case x :: xs1 =>
            ys match {
              case Nil => xs
              case y :: ys1 =>
                if (x < y) x :: merge(xs1, ys)
                else y :: merge(xs, ys1)
            }
        }                                        

  def symmetricalMerge(xs: List[Int], ys: List[Int]): List[Int] =
        (xs, ys) match {
          case (xs, Nil) => xs
          case (Nil, ys) => ys
          case (x :: xs1, y :: ys1) =>
            if (x < y) x :: symmetricalMerge(xs1, ys)
                else y :: symmetricalMerge(xs, ys1)
        }

  def genericMerge[T](xs: List[T], ys: List[T])(lt: (T,T) => Boolean): List[T] =
        (xs, ys) match {
          case (xs, Nil) => xs
          case (Nil, ys) => ys
          case (x :: xs1, y :: ys1) =>
            if (lt(x, y)) x :: genericMerge(xs1, ys)(lt)
                else y :: genericMerge(xs, ys1)(lt)
        }
    
  def genericMsort[T](xs: List[T], genericMergeLists: (List[T], List[T]) => List[T])(lt: (T,T) => Boolean): List[T] = {
    def sort(xs: List[T]): List[T] = {
      val n = xs.length / 2
      if (n == 0) xs
      else {
        val (first, second) = xs splitAt n
        genericMergeLists(sort(first), sort(second))
      }
    }
    sort(xs)
  }
  
  def wholeShebang[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
        def doMerge(xs: List[T], ys: List[T]): List[T] = {
        (xs, ys) match {
          case (xs, Nil) => xs
          case (Nil, ys) => ys
          case (x :: xs1, y :: ys1) =>
            if (ord.lt(x, y)) x :: doMerge(xs1, ys)
                else y :: doMerge(xs, ys1)
        }
        }
        val (first, second) = xs splitAt n
        doMerge(wholeShebang(first), wholeShebang(second))
    } 
  }
  
  def main(args: Array[String]) = {
    val list1 = List(5,1,-9,26,24, 37, 80)
    val list2 = List(1)
    val list3 = List()
    val list4 = List(1,2)
    val list5 = List(2,1)
    val list1Str = List("5","1","-9","26","24","37","80")

    println("merge(5,1,-9,26,24,37,80) => " + msort(list1, merge))
    println("merge(1) => " + msort(list2, merge))                         
    println("merge() => " + msort(list3, merge))                           
    println("merge(1,2) => " + msort(list4, merge))                         
    println("merge(2,1) => " + msort(list5, merge))
    
    println("symmetricalMerge(5,1,-9,26,24, 37, 80) => " + msort(list1, symmetricalMerge))
    println("symmetricalMerge(1) => " + msort(list2, symmetricalMerge))                         
    println("symmetricalMerge() => " + msort(list3, symmetricalMerge))                           
    println("symmetricalMerge(1,2) => " + msort(list4, symmetricalMerge))                         
    println("symmetricalMerge(2,1) => " + msort(list5, symmetricalMerge))
    
    val stringMerge = genericMerge(_: List[String], _: List[String])((a, b) => a < b)
    println("stringMerge(5,1,-9,26,24, 37, 80) => " + msort(list1Str, stringMerge))

    val stringMerge2 = genericMsort(_: List[String], _: (List[String], List[String]) => List[String])((a, b) => a < b)
    val stringMerge3 = genericMsort(_: List[String], stringMerge)((a, b) => a < b)
    
    println("stringMerge3(5,1,-9,26,24, 37, 80) => " + stringMerge3(list1Str))
    
    //val floatyMerge = genericMerge(_: List[Float], _: List[Float])(_: (Float, Float) => Boolean) //Doesn't work
    //val floatyMerge = genericMerge(_: List[Float], _: List[Float]) _ //Doesn't work either
    
    val floatCompare = ((a: Float, b: Float) => a > b)
    val floatyMerge = genericMerge(_: List[Float], _: List[Float])(floatCompare)
    val floatyMerge1 = genericMsort(_: List[Float], floatyMerge)(floatCompare)
    
    val listF: List[Float] = List(5.0f,1.0f,-9.0f,26.0f,24.0f,37.0f,80.0f)
    
    println("floatyMerge1(5,1,-9,26,24, 37, 80) => " + floatyMerge1(listF))
    
    println("wholeShebang(5,1,-9,26,24, 37, 80) Float => " + wholeShebang(listF)(Ordering.Float))
    println("wholeShebang(5,1,-9,26,24, 37, 80) Int => " + wholeShebang(list1)(Ordering.Int))
    println("wholeShebang(5,1,-9,26,24, 37, 80) String => " + wholeShebang(list1Str)(Ordering.String))
    
    println("wholeShebang(5,1,-9,26,24, 37, 80) Float => " + wholeShebang(listF))
    println("wholeShebang(5,1,-9,26,24, 37, 80) Int => " + wholeShebang(list1))
    println("wholeShebang(5,1,-9,26,24, 37, 80) String => " + wholeShebang(list1Str))

    
    // Other example substitutions
    val stringyMerge = genericMerge(_: List[String], _: List[String])((_, _) => true)
    val x = genericMsort(_: List[String], (_: List[String], _: List[String]) => List()) _
    val xx = genericMsort(_: List[String], _: (List[String], List[String]) => List[String]) _
  }
}