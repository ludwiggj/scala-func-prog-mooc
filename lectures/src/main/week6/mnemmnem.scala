package week6

object doIt {

  
  def main(args: Array[String]) = {
    val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI",'5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")      
    
    println ("mnem: [" + mnem + "]")
    
//    val charCode: Map[Char, Char] =
//    for {
//      (digit, str) <- mnem
//      ltr <- str
//    } yield (digit-> ltr)
//  
//    println ("charCode: [" + charCode + "]")
  
    val charCode2: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield (ltr -> digit)
                                                  
    println ("charCode2: [" + charCode2 + "]")
  }
}