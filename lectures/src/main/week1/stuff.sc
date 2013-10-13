object recursionWithoutTailrec {

  def gcd(a: Int, b:Int): Int =
    if (b == 0) a else gcd(b, a % b)              //> gcd: (a: Int, b: Int)Int

  def factorial(n: Int): Int =
    if (n == 0) 1 else n * factorial(n-1)         //> factorial: (n: Int)Int

  gcd(14,21)                                      //> res0: Int = 7
  factorial(4)                                    //> res1: Int = 24
}

