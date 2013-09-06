package week1

object sqrt_1 {
  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double
  
  def sqrtIter(guess: Double, x: Double): Double = {
    if (isGoodEnough(guess, x)) {
      println("guess [" + guess + "] square [" + guess * guess + "] diff [" + abs(guess * guess - x) + "]")
      guess
    }
    else sqrtIter(improve(guess, x), x)
  }                                               //> sqrtIter: (guess: Double, x: Double)Double
  
  def isGoodEnough(guess: Double, x: Double) = {
    // println("guess [" + guess + "] square [" + guess * guess + "] diff [" + abs(guess * guess - x) + "]")
    abs(guess * guess - x) < (0.001 * x)
  }                                               //> isGoodEnough: (guess: Double, x: Double)Boolean
  
  def improve(guess: Double, x: Double) = {
    (guess + x/guess) / 2
  }                                               //> improve: (guess: Double, x: Double)Double
  
  def sqrt(x: Double) = {
    println("x [" + x + "] eps [" + 0.001 * x + "]")
    
    sqrtIter(1.0, x)
  }                                               //> sqrt: (x: Double)Double
  
  sqrt(2)                                         //> x [2.0] eps [0.0020]
                                                  //| guess [1.4142156862745097] square [2.0000060073048824] diff [6.0073048824271
                                                  //| 78E-6]
                                                  //| res0: Double = 1.4142156862745097
  sqrt(4)                                         //> x [4.0] eps [0.0040]
                                                  //| guess [2.000609756097561] square [4.002439396192742] diff [0.002439396192741
                                                  //| 583]
                                                  //| res1: Double = 2.000609756097561
  sqrt(0.001)                                     //> x [0.0010] eps [1.0E-6]
                                                  //| guess [0.03162278245070105] square [0.0010000003699243661] diff [3.699243660
                                                  //| 958834E-10]
                                                  //| res2: Double = 0.03162278245070105
  sqrt(0.1e-20)                                   //> x [1.0E-21] eps [1.0E-24]
                                                  //| guess [3.1633394544890125E-11] square [1.0006716504326844E-21] diff [6.71650
                                                  //| 4326844703E-25]
                                                  //| res3: Double = 3.1633394544890125E-11
  sqrt(1.0e20)                                    //> x [1.0E20] eps [1.0E17]
                                                  //| guess [1.0000021484861237E10] square [1.0000042969768632E20] diff [4.2969768
                                                  //| 63232E14]
                                                  //| res4: Double = 1.0000021484861237E10
  sqrt(1.0e50)                                    //> x [1.0E50] eps [1.0E47]
                                                  //| guess [1.0000003807575104E25] square [1.0000007615151658E50] diff [7.6151516
                                                  //| 57380919E43]
                                                  //| res5: Double = 1.0000003807575104E25
  sqrt(1e-6)                                      //> x [1.0E-6] eps [1.0E-9]
                                                  //| guess [0.0010000001533016628] square [1.0000003066033492E-6] diff [3.0660334
                                                  //| 924284766E-13]
                                                  //| res6: Double = 0.0010000001533016628
  sqrt(1e60)                                      //> x [1.0E60] eps [1.0E57]
                                                  //| guess [1.0000788456669446E30] square [1.0001576975505284E60] diff [1.5769755
                                                  //| 052841273E56]
                                                  //| res7: Double = 1.0000788456669446E30
}