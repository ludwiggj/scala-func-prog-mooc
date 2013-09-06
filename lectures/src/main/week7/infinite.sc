package week7

object infinite {

  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double
 
  def isGoodEnough(guess: Double, x: Double) = {
      abs(guess * guess - x) < (0.001 * x)
  }                                               //> isGoodEnough: (guess: Double, x: Double)Boolean

    
  def sqrt(x: Double) = {
    println("x [" + x + "] eps [" + 0.001 * x + "]")

    def improve(guess: Double) = {
      (guess + x / guess) / 2
    }

    def sqrtIter(guess: Double): Double = {
      if (isGoodEnough(guess, x)) {
        println("guess [" + guess + "] square [" + guess * guess + "] diff [" + abs(guess * guess - x) + "]")
        guess
      } else sqrtIter(improve(guess))
    }

    sqrtIter(1.0)
  }                                               //> sqrt: (x: Double)Double
  
  // Stream of all integers from a given start point
  def from(n: Int): Stream[Int] = n #:: from(n+1) //> from: (n: Int)Stream[Int]
  
  // all nat numbers
  val nats = from(0)                              //> nats  : Stream[Int] = Stream(0, ?)
  (nats take 10) toList                           //> res0: List[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  
  //all multiples of 4
  (nats map (_ * 4)) take 10 toList               //> res1: List[Int] = List(0, 4, 8, 12, 16, 20, 24, 28, 32, 36)
  
  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head !=0))
                                                  //> sieve: (s: Stream[Int])Stream[Int]
    

    
  val primes = sieve(from(2))                     //> primes  : Stream[Int] = Stream(2, ?)
  primes take 200 toList                          //> res2: List[Int] = List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 
                                                  //| 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 1
                                                  //| 31, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 2
                                                  //| 11, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 2
                                                  //| 93, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 3
                                                  //| 89, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 4
                                                  //| 79, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 5
                                                  //| 87, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 6
                                                  //| 73, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 7
                                                  //| 73, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 8
                                                  //| 81, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 9
                                                  //| 91, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 
                                                  //| 1069, 1087, 1091, 1093,
                                                  //| Output exceeds cutoff limit.
  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }                                               //> sqrtStream: (x: Double)Stream[Double]


  sqrt(2)                                         //> x [2.0] eps [0.0020]
                                                  //| guess [1.4142156862745097] square [2.0000060073048824] diff [6.007304882427
                                                  //| 178E-6]
                                                  //| res3: Double = 1.4142156862745097
  sqrtStream(2).take(10).toList                   //> res4: List[Double] = List(1.0, 1.5, 1.4166666666666665, 1.4142156862745097,
                                                  //|  1.4142135623746899, 1.414213562373095, 1.414213562373095, 1.41421356237309
                                                  //| 5, 1.414213562373095, 1.414213562373095)
  sqrtStream(2).filter(isGoodEnough(_, 2)).take(10).toList
                                                  //> res5: List[Double] = List(1.4142156862745097, 1.4142135623746899, 1.4142135
                                                  //| 62373095, 1.414213562373095, 1.414213562373095, 1.414213562373095, 1.414213
                                                  //| 562373095, 1.414213562373095, 1.414213562373095, 1.414213562373095)
  
}